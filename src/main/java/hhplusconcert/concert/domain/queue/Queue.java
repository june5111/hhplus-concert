package hhplusconcert.concert.domain.queue;

import hhplusconcert.concert.domain.constant.WaitStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Queue {

    private Long queueId;
    private Long userId;
    private WaitStatus waitStatus;
    private LocalDateTime expiredAt;
    private int waitingNum;
    private boolean isExpired;

    @Builder
    public Queue(Long queueId, Long userId, WaitStatus waitStatus, boolean isExpired) {
        this.queueId = queueId;
        this.userId = userId;
        this.waitStatus = waitStatus;
        this.isExpired = isExpired;
    }

}
