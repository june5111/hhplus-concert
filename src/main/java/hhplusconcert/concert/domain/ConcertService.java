package hhplusconcert.concert.domain;

import hhplusconcert.concert.controller.DTO.ConcertDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConcertService {

    private List<ConcertDTO> concertList = createDummyConcertList();

    private List<ConcertDTO> createDummyConcertList() {
        List<ConcertDTO> concertList = new ArrayList<>();

        concertList.add(ConcertDTO.builder()
                        .concertId(1)
                        .concertTitle("토트넘 손흥민 주전 경기")
                        .concertHall("수원월드컵경기장")
                        .concertHost("대한축구협회")
                        .startDate("2024-07-31 17:00:00")
                .build());

        concertList.add(ConcertDTO.builder()
                .concertId(2)
                .concertTitle("뉴진스 단독 콘서트")
                .concertHall("도쿄돔")
                .concertHost("어도어")
                .startDate("2024-07-30 17:00:00")
                .build());

        concertList.add(ConcertDTO.builder()
                .concertId(3)
                .concertTitle("출근전 밤새기 콘서트")
                .concertHall("MyRoom")
                .concertHost("우리모두")
                .startDate("2024-07-05 06:00:00")
                .build());

        return concertList;

    }//createDummyConcertList

    public List<ConcertDTO> getAvailableConcerts() {
        return concertList;
    }


}
