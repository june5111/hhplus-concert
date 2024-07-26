package hhplusconcert.concert.domain.concert;

import hhplusconcert.concert.infrastructure.concert.ConcertSeatEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConcertSeatRepository {
    List<ConcertSeat> findReservedSeat(Long concertOptionId);
    ConcertSeat findByconcertOptionIdAndSeatNum(Long concertOptionId, int seatNum);

    void saveConcertSeat(ConcertSeatEntity concertSeatEntity);

    List<ConcertSeat> findExpiredSeats(LocalDateTime expiredTime, SeatStatus seatStatus);

    void updateTempStatus(List<Long> expiredSeatIds);

}
