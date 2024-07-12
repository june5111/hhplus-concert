package hhplusconcert.concert.domain.concert;

import hhplusconcert.concert.controller.DTO.ConcertSeatDTO;
import hhplusconcert.concert.controller.DTO.ConcertSeatResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class ConcertSeat {
    private Long seatId;
    private Long concertOptionId;
    private Long userId;
    private int seatNum;
    private int price;
    private LocalDateTime updatedAt;
    private SeatStatus status;

    @Builder
    public ConcertSeat(Long seatId, Long concertOptionId, Long userId, int seatNum, int price, LocalDateTime updatedAt, SeatStatus status) {
        this.seatId = seatId;
        this.concertOptionId = concertOptionId;
        this.userId = userId;
        this.seatNum = seatNum;
        this.price = price;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public static ConcertSeatResponse toSeatResponse(Long concertOptionId, List<ConcertSeat> availableReservedSeats){
        return ConcertSeatResponse.builder()
                .concertOptionId(concertOptionId)
                .seats(
                        availableReservedSeats.stream()
                                .map(concertSeat ->
                                        ConcertSeatDTO.builder()
                                                .seatNo(concertSeat.seatNum)
                                                .price(concertSeat.price)
                                                .build()
                                ).collect(Collectors.toList())
                ).build();
    }

}
