package hhplusconcert.concert.infrastructure.queue;

import hhplusconcert.concert.domain.constant.WaitStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "queue", uniqueConstraints = {
        @UniqueConstraint(
                name = "userId_status_unique",
                columnNames = {"userId","status"}
        )})
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;
    private Long userId;

    @Enumerated(EnumType.STRING)
    WaitStatus waitStatus;

    @Column(nullable = true)
    private LocalDateTime expiredAt;

    private boolean isExpired;

    @Builder
    public QueueEntity(Long queueId, Long userId, WaitStatus waitStatus, LocalDateTime expiredAt, boolean isExpired) {
        this.queueId = queueId;
        this.userId = userId;
        this.waitStatus = waitStatus;
        this. expiredAt = expiredAt;
        this.isExpired = isExpired;

    }
}
