package hhplusconcert.concert;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhplusconcert.concert.controller.ConcertController;
import hhplusconcert.concert.controller.DTO.ConcertDTO;
import hhplusconcert.concert.domain.ConcertService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ConcertControllerTest {

    @Autowired
    private ConcertController concertController;

    @MockBean
    private ConcertService concertService;


    @Test
    public void getAvailableConcerts_returnsConcertList() throws Exception {
        // Given
        List<ConcertDTO> mockConcerts = Arrays.asList(
                ConcertDTO.builder()
                        .concertId(1)
                        .concertTitle("토트넘 손흥민 주전 경기")
                        .concertHall("수원월드컵경기장")
                        .concertHost("대한축구협회")
                        .startDate("2024-07-31 17:00:00")
                        .build(),

                ConcertDTO.builder()
                        .concertId(2)
                        .concertTitle("뉴진스 단독 콘서트")
                        .concertHall("도쿄돔")
                        .concertHost("어도어")
                        .startDate("2024-07-30 17:00:00")
                        .build(),

                ConcertDTO.builder()
                        .concertId(3)
                        .concertTitle("출근전 밤새기 콘서트")
                        .concertHall("MyRoom")
                        .concertHost("우리모두")
                        .startDate("2024-07-05 06:00:00")
                        .build()
        );

// When
        Mockito.when(concertService.getAvailableConcerts()).thenReturn(mockConcerts);

        List<ConcertDTO> actualConcerts = concertController.getAvailableConcerts();

        // Then
        assertThat(actualConcerts).isEqualTo(mockConcerts);
    }

}
