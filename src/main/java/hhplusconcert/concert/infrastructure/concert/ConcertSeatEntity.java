package hhplusconcert.concert.infrastructure.concert;

import hhplusconcert.concert.domain.concert.SeatStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "concert_seat",
uniqueConstraints = {
        @UniqueConstraint(name = "concertOptionId_seatNum",
        columnNames = {"concertOptionId","seatNum"})
})
public class ConcertSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    private Long concertOptionId;

    private Long userId;

    private int seatNum;

    private int price;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Builder
    public ConcertSeatEntity(Long seatId, Long userId, Long concertOptionId, int seatNum, int price, SeatStatus status) {
        this.seatId = seatId;
        this.concertOptionId = concertOptionId;
        this.userId = userId;
        this.seatNum = seatNum;
        this.price = price;
        this.status = status;

    }
}
