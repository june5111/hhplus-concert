package hhplusconcert.concert.controller;

import hhplusconcert.concert.controller.DTO.ConcertDTO;
import hhplusconcert.concert.domain.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/concert/reservation/dates")
    public List<ConcertDTO> getAvailableConcerts() {
        return concertService.getAvailableConcerts();
    }


}
