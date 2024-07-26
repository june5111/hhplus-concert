package hhplusconcert.concert.infrastructure.concert;

import hhplusconcert.concert.controller.DTO.ConcertInfoResponse;
import hhplusconcert.concert.domain.concert.ConcertOptionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ConcertOptionRepositoryImpl implements ConcertOptionRepository {
    @Override
    public List<ConcertInfoResponse> availableConcerts() {
        return null;
    }

    @Override
    public boolean existFindById(Long id) {
        return false;
    }
}
