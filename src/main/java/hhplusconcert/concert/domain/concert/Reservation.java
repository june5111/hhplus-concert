package hhplusconcert.concert.domain.concert;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Reservation {

    private Long reservationId;
    private Long userId;
    private int seatNum;
    private int reservedPrice;
    private String title;
    private String host;
    private LocalDateTime startDate;

    @Builder
    public Reservation(Long reservationId, Long userId, int seatNum, int reservedPrice, String title, String host, LocalDateTime startDate) {

        this.reservationId = reservationId;
        this.userId = userId;
        this.seatNum = seatNum;
        this.reservedPrice = reservedPrice;
        this.title = title;
        this.host = host;
        this.startDate = startDate;

    }

}
