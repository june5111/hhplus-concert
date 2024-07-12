package hhplusconcert.concert.domain.Point;

import hhplusconcert.concert.domain.constant.TransactionType;
import hhplusconcert.concert.infrastructure.point.PointHistoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointHistory {

    private Long userId;
    private Long point;
    private TransactionType transactionType;

    @Builder
    public PointHistory(Long userId, Long point, TransactionType transactionType) {
        this.userId = userId;
        this.point = point;
        this.transactionType = transactionType;
    }

    public static PointHistoryEntity toEntity(PointHistory pointHistory) {
        return PointHistoryEntity.builder()
                .userId(pointHistory.userId)
                .point(pointHistory.point)
                .type(pointHistory.transactionType)
                .build();
    }

}
