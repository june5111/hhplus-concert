package hhplusconcert.concert.infrastructure.point;

import hhplusconcert.concert.domain.Point.Point;
import hhplusconcert.concert.domain.Point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

    @Override
    public Point findPointByUserId(Long userId) {
        return null;
    }

    @Override
    public Point updatePoint(PointEntity pointEntity) {
        return null;
    }
}
