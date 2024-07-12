package hhplusconcert.concert.domain.queue;

import hhplusconcert.concert.controller.DTO.QueueRegisterResponse;
import hhplusconcert.concert.controller.DTO.QueueStatusResponse;
import hhplusconcert.concert.domain.constant.WaitStatus;
import hhplusconcert.concert.infrastructure.queue.QueueEntity;

public class QueueMapper {

    public static Queue toDomain(QueueEntity queueEntity) {
        return Queue.builder()
                .queueId(queueEntity.getQueueId())
                .userId(queueEntity.getUserId())
                .waitStatus(queueEntity.getWaitStatus())
                .expiredAt(queueEntity.getExpiredAt())
                .build();
    }

    public static QueueEntity toEntity(Queue queue) {
        return QueueEntity.builder()
                .queueId(queue.getQueueId())
                .userId(queue.getUserId())
                .waitStatus(queue.getWaitStatus())
                .expiredAt(queue.getExpiredAt())
                .build();
    }

    public static QueueRegisterResponse toQueueRegisterResponse(Queue queue) {
        return QueueRegisterResponse.builder()
                .waitingNum(queue.getWaitingNum())
                .expiredAt(queue.getExpiredAt())
                .message(queue.getWaitStatus() == WaitStatus.ONGOING ? "이미 대기열에 진입하였습니다." : null)
                .build();
    }

    public static QueueStatusResponse toQueueStatusResponse(Queue queue) {
        return QueueStatusResponse.builder()
                .waitingNum(queue.getWaitingNum())
                .waitStatus(queue.getWaitStatus())
                .build();
    }

}
