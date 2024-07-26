package hhplusconcert.concert;

import hhplusconcert.concert.controller.DTO.ConcertInfoResponse;
import hhplusconcert.concert.controller.DTO.ConcertSeatResponse;
import hhplusconcert.concert.controller.DTO.ReservationRequest;
import hhplusconcert.concert.controller.DTO.ReservationResponse;
import hhplusconcert.concert.domain.concert.*;
import hhplusconcert.concert.infrastructure.concert.ConcertSeatEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConcertServiceTest {

    @InjectMocks
    private ConcertService concertService;

    @Mock
    private ConcertOptionRepository concertOptionRepository;

    @Mock
    private ConcertSeatRepository concertSeatRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAvailableConcerts_Success() {
        // Given
        List<Concert> reservedConcerts = Arrays.asList(new Concert(), new Concert());
        when(concertOptionRepository.availableConcerts()).thenReturn(reservedConcerts);

        // When
        List<ConcertInfoResponse> response = concertService.findAvailableConcerts();

        // Then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        verify(concertOptionRepository, times(1)).availableConcerts();
    }

    @Test
    void testFindAvailableConcerts_NoConcertsAvailable() {
        // Given
        when(concertOptionRepository.availableConcerts()).thenReturn(Collections.emptyList());

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            concertService.findAvailableConcerts();
        });

        assertEquals("예약가능한 콘서트가 없습니다.", exception.getMessage());
        verify(concertOptionRepository, times(1)).availableConcerts();
    }

    @Test
    void testFindAvailableSeats_Success() {
        // Given
        Long concertOptionId = 1L;
        List<ConcertSeat> reservedSeats = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            reservedSeats.add(new ConcertSeat(i, concertOptionId, 1000, SeatStatus.RESERVED));
        }

        when(concertSeatRepository.findReservedSeat(concertOptionId)).thenReturn(reservedSeats);

        // When
        ConcertSeatResponse response = concertService.findAvailableSeats(concertOptionId);

        // Then
        assertNotNull(response);
        assertEquals(5, response.seats().size());
        verify(concertSeatRepository, times(1)).findReservedSeat(concertOptionId);
    }

    @Test
    void testFindAvailableSeats_NoAvailableSeats() {
        // Given
        Long concertOptionId = 1L;
        List<ConcertSeat> reservedSeats = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            reservedSeats.add(new ConcertSeat(i, concertOptionId, 1000, SeatStatus.RESERVED));
        }

        when(concertSeatRepository.findReservedSeat(concertOptionId)).thenReturn(reservedSeats);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            concertService.findAvailableSeats(concertOptionId);
        });

        assertEquals("이용가능한 좌석이 없습니다.", exception.getMessage());
        verify(concertSeatRepository, times(1)).findReservedSeat(concertOptionId);
    }

    @Test
    void testReservationAvailableSeat_Success() {
        // Given
        Long concertOptionId = 1L;
        Long userId = 1L;
        int seatNum = 1;
        ReservationRequest request = new ReservationRequest(concertOptionId, userId, seatNum);
        ConcertSeat concertSeat = new ConcertSeat(seatNum, concertOptionId, 1000, SeatStatus.AVAILABLE);

        when(concertSeatRepository.findByconcertOptionIdAndSeatNum(concertOptionId, seatNum)).thenReturn(concertSeat);

        // When
        ReservationResponse response = concertService.reservationAvailableSeat(request);

        // Then
        assertNotNull(response);
        verify(concertSeatRepository, times(1)).findByconcertOptionIdAndSeatNum(concertOptionId, seatNum);
        verify(concertSeatRepository, times(1)).saveConcertSeat(any(ConcertSeatEntity.class));
    }

    @Test
    void testReservationAvailableSeat_AlreadyReserved() {
        // Given
        Long concertOptionId = 1L;
        Long userId = 1L;
        int seatNum = 1;
        ReservationRequest request = new ReservationRequest(concertOptionId, userId, seatNum);
        ConcertSeat concertSeat = new ConcertSeat(seatNum, concertOptionId, 1000, SeatStatus.RESERVED);

        when(concertSeatRepository.findByconcertOptionIdAndSeatNum(concertOptionId, seatNum)).thenReturn(concertSeat);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            concertService.reservationAvailableSeat(request);
        });

        assertEquals("이미 선택된 좌석입니다.", exception.getMessage());
        verify(concertSeatRepository, times(1)).findByconcertOptionIdAndSeatNum(concertOptionId, seatNum);
        verify(concertSeatRepository, never()).saveConcertSeat(any(ConcertSeatEntity.class));
    }

    @Test
    void testUpdateStatustoExpired_Success() {
        // Given
        List<ConcertSeat> expiredSeats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            expiredSeats.add(new ConcertSeat(i, 1L, 1000, SeatStatus.TEMPORARY));
        }

        when(concertSeatRepository.findExpiredSeats(any(LocalDateTime.class), eq(SeatStatus.TEMPORARY)))
                .thenReturn(expiredSeats);

        // When
        concertService.updateStatustoExpired();

        // Then
        verify(concertSeatRepository, times(1)).findExpiredSeats(any(LocalDateTime.class), eq(SeatStatus.TEMPORARY));
        verify(concertSeatRepository, times(1)).updateTempStatus(anyList());
    }
}
