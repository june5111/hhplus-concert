package hhplusconcert.concert.controller;

import hhplusconcert.concert.Facade.ConcertFacade;
import hhplusconcert.concert.controller.DTO.ConcertInfoResponse;
import hhplusconcert.concert.controller.DTO.ConcertSeatResponse;
import hhplusconcert.concert.controller.DTO.ReservationRequest;
import hhplusconcert.concert.controller.DTO.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertController {

    private ConcertFacade concertFacade;

    @GetMapping("/concert/reservation/dates")
    public List<ConcertInfoResponse> getAvailableConcerts() {
        return concertFacade.avilableConcerts();
    }

    @GetMapping("/concert/{concertOptionId}/reservation/seats")
    public ConcertSeatResponse avilableSeats(@PathVariable Long concertOptionId) {
        return concertFacade.availableSeats(concertOptionId);
    }

    @PostMapping("/concert/reservation")
    public ReservationResponse reservation(@RequestBody ReservationRequest request) {
        return concertFacade.reservationConcert(request);
    }




}
