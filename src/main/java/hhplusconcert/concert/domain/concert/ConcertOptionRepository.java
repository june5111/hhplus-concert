package hhplusconcert.concert.domain.concert;

import java.util.List;

public interface ConcertOptionRepository {
    List<Concert> availableConcerts();

    boolean existFindById(Long id);

}
