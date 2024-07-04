package hhplusconcert.concert.controller.DTO;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public class ConcertDTO {
    private  int concertId;
    private String concertTitle;
    private String concertHost;
    private String concertHall;
    private String startDate;



}
