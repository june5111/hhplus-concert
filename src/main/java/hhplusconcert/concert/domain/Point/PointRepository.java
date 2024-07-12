package hhplusconcert.concert.domain.Point;

import hhplusconcert.concert.infrastructure.point.PointEntity;

public interface PointRepository {

    Point findPointByUserId(Long userId);

    Point updatePoint(PointEntity pointEntity);

}
