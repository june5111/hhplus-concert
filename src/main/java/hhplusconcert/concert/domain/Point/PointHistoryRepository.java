package hhplusconcert.concert.domain.Point;

import hhplusconcert.concert.infrastructure.point.PointHistoryEntity;

public interface PointHistoryRepository {

    void save(PointHistoryEntity pointHistoryEntity);

}
