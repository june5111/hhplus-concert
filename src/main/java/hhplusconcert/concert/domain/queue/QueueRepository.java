package hhplusconcert.concert.domain.queue;

import hhplusconcert.concert.domain.constant.WaitStatus;
import hhplusconcert.concert.infrastructure.queue.QueueEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueRepository {

    Queue save(QueueEntity queueEntity);

    Queue findByUserIdAndStatus(Long userId, List<WaitStatus> statuses);

    List<Queue> findByStatus(WaitStatus waitStatus);

    List<Queue> findExpiredOngoingStatus(WaitStatus waitStatus, LocalDateTime now);

    List<Queue> findWaitStatusQueue(WaitStatus waitStatus, int size);

    void changeWaitStatusToOthers(List<QueueEntity> entities);

    Queue findById(Long concertWaitingId);

    int countWaitingNum(Long concertWaitingId);

}
