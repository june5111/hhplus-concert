package hhplusconcert.concert.domain.concert;

import hhplusconcert.concert.common.ResponseCode;
import hhplusconcert.concert.controller.DTO.ConcertInfoResponse;
import hhplusconcert.concert.controller.DTO.ConcertSeatDTO;
import hhplusconcert.concert.controller.DTO.ConcertSeatResponse;
import hhplusconcert.concert.controller.DTO.ReservationResponse;
import hhplusconcert.concert.infrastructure.concert.ConcertEntity;
import hhplusconcert.concert.infrastructure.concert.ConcertSeatEntity;
import hhplusconcert.concert.infrastructure.concert.ReservationEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ConcertMapper {

    public static ConcertInfoResponse toConcertInfoResponse(Concert concert) {
        return ConcertInfoResponse.builder()
                .concertId(concert.getConcertId())
                .host(concert.getHost())
                .title(concert.getTitle())
                .build();
    }

    public static List<ConcertInfoResponse> toConcertInfoResponses(List<Concert> concerts) {
        return concerts.stream()
                .map(ConcertMapper::toConcertInfoResponse)
                .collect(Collectors.toList());
    }

    public static Concert toConcertDomain(ConcertEntity concertEntity) {
        return Concert.builder()
                .concertId(concertEntity.getConcertId())
                .host(concertEntity.getHost())
                .title(concertEntity.getTitle())
                .build();
    }

    public static ConcertSeatResponse toSeatResponse(Long concertOptionId, List<ConcertSeat> availableReservedSeats) {
        return ConcertSeatResponse.builder()
                .concertOptionId(concertOptionId)
                .seats(
                        availableReservedSeats.stream()
                                .map(concertSeat ->
                                        ConcertSeatDTO.builder()
                                                .seatNo(concertSeat.getSeatNum())
                                                .price(concertSeat.getPrice())
                                                .build()
                                ).collect(Collectors.toList())
                ).build();
    }

    public static ReservationResponse toReservationResponse(LocalDateTime expiredAt) {
        String message = String.format("좌석이 임시배당 되었습니다. [%s] 뒤 만료됩니다.", expiredAt);

        return ReservationResponse.builder()
                .code(ResponseCode.SUCCESS)
                .message(message)
                .build();
    }

    public static ConcertSeatEntity toEntity(ConcertSeat concertSeat) {
        return ConcertSeatEntity.builder()
                .seatId(concertSeat.getSeatId())
                .concertOptionId(concertSeat.getConcertOptionId())
                .userId(concertSeat.getUserId())
                .status(concertSeat.getStatus())
                .seatNum(concertSeat.getSeatNum())
                .price(concertSeat.getPrice())
                .build();
    }

    public static ReservationEntity toReservationEntity(Reservation reservation) {
        return ReservationEntity.builder()
                .reservationId(reservation.getReservationId())
                .userId(reservation.getUserId())
                .seatNum(reservation.getSeatNum())
                .reservedPrice(reservation.getReservedPrice())
                .title(reservation.getTitle())
                .host(reservation.getHost())
                .startDate(reservation.getStartDate())
                .build();
    }

    public static Reservation toReservationDomain(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .reservationId(reservationEntity.getReservationId())
                .userId(reservationEntity.getUserId())
                .seatNum(reservationEntity.getSeatNum())
                .reservedPrice(reservationEntity.getReservedPrice())
                .title(reservationEntity.getTitle())
                .host(reservationEntity.getHost())
                .startDate(reservationEntity.getStartDate())
                .build();
    }

}
