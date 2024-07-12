package hhplusconcert.concert.infrastructure.queue;

import hhplusconcert.concert.domain.constant.WaitStatus;
import hhplusconcert.concert.domain.queue.Queue;
import hhplusconcert.concert.domain.queue.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueueRepositoryImpl implements QueueRepository {

    @Override
    public Queue save(QueueEntity queueEntity) {
        return null;
    }

    @Override
    public Queue findByUserIdAndStatus(Long userId, List<WaitStatus> statuses) {
        return null;
    }

    @Override
    public List<Queue> findByStatus(WaitStatus waitStatus) {
        return null;
    }

    @Override
    public List<Queue> findExpiredOngoingStatus(WaitStatus waitStatus, LocalDateTime now) {
        return null;
    }
}
