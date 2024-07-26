package hhplusconcert.concert.domain.queue;

import hhplusconcert.concert.controller.DTO.QueueRegisterRequest;
import hhplusconcert.concert.controller.DTO.QueueRegisterResponse;
import hhplusconcert.concert.controller.DTO.QueueStatusResponse;
import hhplusconcert.concert.domain.constant.WaitStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class QueueService {

    private final QueueRepository queueRepository;
    private final int QUEUE_LIMIT = 5;
    private final int EXPIRED_TIME = 5;

    @Transactional
    public QueueRegisterResponse register(QueueRegisterRequest registerRequest) {

        Long userId = registerRequest.userId();

        judgingUserStatus(userId);

        Queue queue = Queue.builder()
                .userId(userId)
                .build();

        assignQueueStatus(queue);



        return QueueMapper.toQueueRegisterResponse(queueRepository.save(QueueMapper.toEntity(queue)));
    }

    /**
     * 유저 대기 상태 판단
     * @param userId
     */
    public void judgingUserStatus(Long userId) {
        Queue queue = queueRepository.findByUserIdAndStatus(userId, List.of(WaitStatus.WAIT, WaitStatus.ONGOING));

        if(queue == null) {
            return;
        }

        if(queue.getWaitStatus() == WaitStatus.WAIT) {
            throw new IllegalArgumentException("이미 대기 중 입니다.");
        }

        if(queue.getWaitStatus() == WaitStatus.ONGOING) {
            String message = String.format("[%s] 후 대기열 만료",queue.getExpiredAt());
            throw new IllegalArgumentException(message);
        }

    }//judgingUserStatus

    /**
     * 처음 대기열 진입한 유저 대기상태 등록
     * @param queue
     */
    public void assignQueueStatus(Queue queue) {
         List<Queue> ongoingStatus = queueRepository.findByStatus(WaitStatus.ONGOING);

         queue.setWaitStatus(WaitStatus.WAIT);

         if(ongoingStatus.size() < QUEUE_LIMIT) {

             queue.setWaitStatus(WaitStatus.ONGOING);
             queue.setExpiredAt(LocalDateTime.now().plusMinutes(EXPIRED_TIME));



         }

    }//assignQueueStatus

    /**
     * 대기열 번호 조회
     * @param waitingId
     * @return
     */
    public QueueStatusResponse checkWaitingNum(Long waitingId) {
        Queue queue = queueRepository.findById(waitingId);
        if(queue == null) {
            throw new IllegalArgumentException("존재하지 않는 번호표입니다.");
        }

        if(queue.getWaitStatus() == WaitStatus.ONGOING) {
            throw new IllegalArgumentException("이미 대기열이 활성화 되었습니다.");
        }

        updateMyNum(queue,waitingId);

        return QueueMapper.toQueueStatusResponse(queue);
    }

    /**
     * 번호표 조회
     * @param queue
     * @param waitingId
     */
    public void updateMyNum(Queue queue,Long waitingId) {
        int myNum = queueRepository.countWaitingNum(waitingId);
        queue.setWaitingNum(myNum);
    }

    public boolean isOngoingQueue(Long waitingId) {
        Queue queue = Optional.ofNullable(queueRepository.findById(waitingId))
                .orElseThrow(() -> new IllegalArgumentException("번호표가 없습니다."));

        boolean isOngoing = false;
        if(queue.getWaitStatus() == WaitStatus.ONGOING) {
            isOngoing = true;
        }

        return isOngoing;
    }


    /**
     * Scheduler를 이용한 대기열 만료
     */
    @Transactional
    @Scheduled(cron = "0 5 * * * *")
    public void changeQueueStateByScheduling() {
        List<Queue> expireOngoingStatus = queueRepository.findExpiredOngoingStatus(WaitStatus.ONGOING,LocalDateTime.now());

        if(!expireOngoingStatus.isEmpty()) {
            expireOngoingStatus.forEach(queue -> {
                queue.setWaitStatus(null);
                queue.setExpired(true);
            });

            activeQueueStatusBySize(expireOngoingStatus.size());
            
        }

    }

    //지정한 크기만큼 대기 중 사용자 활성화
    public void activeQueueStatusBySize(int availavleSize) {
         List<Queue> waitStatusQueue = queueRepository.findWaitStatusQueue(WaitStatus.WAIT, availavleSize);
         waitStatusQueue.forEach(queue -> {
              queue.setWaitStatus(WaitStatus.ONGOING);
              queue.setExpiredAt(LocalDateTime.now().plusMinutes(EXPIRED_TIME));
         });
         queueRepository.changeWaitStatusToOthers(
                 waitStatusQueue.stream()
                         .map(QueueMapper::toEntity)
                         .toList()
         );
        
    }
    



}//class
