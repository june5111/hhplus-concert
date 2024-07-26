package hhplusconcert.concert.infrastructure.point;

import hhplusconcert.concert.domain.Point.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointHistoryRepositoryImpl implements PointHistoryRepository {


    @Override
    public void save(PointHistoryEntity pointHistoryEntity) {

    }
}
