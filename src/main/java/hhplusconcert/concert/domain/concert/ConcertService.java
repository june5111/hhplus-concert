package hhplusconcert.concert.domain.concert;

import hhplusconcert.concert.controller.DTO.ConcertInfoResponse;
import hhplusconcert.concert.controller.DTO.ConcertSeatResponse;
import hhplusconcert.concert.controller.DTO.ReservationRequest;
import hhplusconcert.concert.controller.DTO.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertOptionRepository concertOptionRepository;
    private final ConcertSeatRepository concertSeatRepository;
    private final int SEAT_SIZE = 50;
    private final int EXPIRE_TIME = 10;

    //예약가능 콘서트 찾기
    public List<ConcertInfoResponse> findAvailableConcerts() {
        List<Concert> reservedConcerts = concertOptionRepository.availableConcerts();

        if(reservedConcerts.isEmpty()) {
            throw new IllegalArgumentException("예약가능한 콘서트가 없습니다.");
        }

        return ConcertMapper.toConcertInfoResponses(reservedConcerts);
    }

    /**
     * 예약가능한 좌석 조회
     * @param concertOptionId
     * @return
     */
    public ConcertSeatResponse findAvailableSeats(Long concertOptionId) {
        List<ConcertSeat> reservedSeats = concertSeatRepository.findReservedSeat(concertOptionId);

        if(reservedSeats.size() == SEAT_SIZE) {
            throw new IllegalArgumentException("이용가능한 좌석이 없습니다.");
        }

        List<ConcertSeat> concertSeats = calAvailableSeats(reservedSeats, SEAT_SIZE);
        return ConcertMapper.toSeatResponse(concertOptionId,concertSeats);

    }

    //사용가능 좌석 찾기
    public List<ConcertSeat> calAvailableSeats(List<ConcertSeat> reservedSeats, int SEAT_SIZE) {
        Set<Integer> reservedSeatNums = reservedSeats.stream()
                .map(ConcertSeat::getSeatNum)
                .collect(Collectors.toSet());


        List<ConcertSeat> availableSeats = new ArrayList<>();
        for (int i = 1; i <= SEAT_SIZE; i++) {
            if (!reservedSeatNums.contains(i)) {
                availableSeats.add(ConcertSeat.builder()
                        .seatNum(i)
                        .price(1000)
                        .build());
            }
        }

        return availableSeats;

    }

    //사용가능한 좌석 예약
    @Transactional
    public ReservationResponse reservationAvailableSeat(ReservationRequest request) {
        Long concertOptionId = request.concertOptionId();
        Long userId = request.userId();
        int seatNum = request.seatNum();

        ConcertSeat concertSeat = checkAvailableSeat(concertOptionId,seatNum);

        if(concertSeat.getStatus() == SeatStatus.AVAILABLE) {
            updateConcertSeat(concertSeat,userId);
            return ConcertMapper.toReservationResponse(LocalDateTime.now().plusMinutes(EXPIRE_TIME));
        }else {
            throw new IllegalArgumentException("이미 선택된 좌석입니다.");
        }

    }

    //좌석 검색
    public ConcertSeat checkAvailableSeat(Long concertOptionId, int seatNum) {
        return concertSeatRepository.findByconcertOptionIdAndSeatNum(concertOptionId,seatNum);

    }

    //특정 좌석 임시예약으로 변경
    public void updateConcertSeat(ConcertSeat concertSeat, Long userId) {
        concertSeat.setStatus(SeatStatus.TEMPORARY);
        concertSeat.setUserId(userId);

        concertSeatRepository.saveConcertSeat(ConcertMapper.toEntity(concertSeat));

    }

    /**
     * Scheduler를 이용한 좌석 만료
     */
    @Transactional
    @Scheduled(cron = "0 10 * * * *")
    public void updateStatustoExpired() {
        List<ConcertSeat> expiredSeats = findExpiredSeats();
        List<Long> expiredSeatIds = expiredSeats.stream()
                .map(ConcertSeat::getSeatId)
                .toList();
        concertSeatRepository.updateTempStatus(expiredSeatIds);
    }

    //만료된 SeatId 검색
    public List<ConcertSeat> findExpiredSeats() {
        LocalDateTime expiredSeats = LocalDateTime.now().minusMinutes(EXPIRE_TIME);
        return concertSeatRepository.findExpiredSeats(expiredSeats,SeatStatus.TEMPORARY);
    }








}
