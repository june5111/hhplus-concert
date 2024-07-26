package hhplusconcert.concert.Facade;

import hhplusconcert.concert.controller.DTO.ConcertInfoResponse;
import hhplusconcert.concert.controller.DTO.ConcertSeatResponse;
import hhplusconcert.concert.controller.DTO.ReservationRequest;
import hhplusconcert.concert.controller.DTO.ReservationResponse;
import hhplusconcert.concert.domain.concert.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ConcertFacade {
    private final ConcertService concertService;
    public List<ConcertInfoResponse> avilableConcerts() {
        return concertService.findAvailableConcerts();
    }

    public ConcertSeatResponse availableSeats(Long concertOptionId) {
        return concertService.findAvailableSeats(concertOptionId);
    }

    public ReservationResponse reservationConcert(ReservationRequest request) {
        return concertService.reservationAvailableSeat(request);
    }

}
