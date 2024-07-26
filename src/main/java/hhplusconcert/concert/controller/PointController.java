package hhplusconcert.concert.controller;

import hhplusconcert.concert.Facade.PointFacade;
import hhplusconcert.concert.controller.DTO.PointChargeRequest;
import hhplusconcert.concert.controller.DTO.PointChargeResponse;
import hhplusconcert.concert.controller.DTO.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PointController {

    private final PointFacade pointFacade;

    /**
     * 포인트 충전
     * @param pointChargeRequest
     * @return
     */
    @PatchMapping("/point")
    public PointChargeResponse chargePoint(@RequestBody PointChargeRequest pointChargeRequest){

        return pointFacade.chargePoint(pointChargeRequest);
    }

    /**
     * 포인트 조회
     * @param userId
     * @return
     */
    @GetMapping("/point/{userId}")
    public PointResponse getPoint(@PathVariable Long userId) {
        return pointFacade.getPoint(userId);
    }

}
