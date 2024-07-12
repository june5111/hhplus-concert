package hhplusconcert.concert.Facade;

import hhplusconcert.concert.controller.DTO.PointChargeRequest;
import hhplusconcert.concert.controller.DTO.PointChargeResponse;
import hhplusconcert.concert.controller.DTO.PointResponse;
import hhplusconcert.concert.domain.Point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointFacade {

    private final PointService pointService;

    public PointChargeResponse chargePoint (PointChargeRequest pointChargeRequest) {
        return pointService.chargePoint(pointChargeRequest);
    }

    public PointResponse getPoint (Long userId) {
        return pointService.findPoint(userId);
    }

}
