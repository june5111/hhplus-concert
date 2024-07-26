package hhplusconcert.concert.infrastructure.concert;

import hhplusconcert.concert.domain.concert.ConcertSeat;
import hhplusconcert.concert.domain.concert.ConcertSeatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConcertSeatRepositoryImpl implements ConcertSeatRepository {
    @Override
    public List<ConcertSeat> findReservedSeat(Long concertOptionId) {
        return null;
    }
}
