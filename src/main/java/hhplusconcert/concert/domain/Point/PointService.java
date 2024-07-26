package hhplusconcert.concert.domain.Point;

import hhplusconcert.concert.controller.DTO.PointChargeRequest;
import hhplusconcert.concert.controller.DTO.PointChargeResponse;
import hhplusconcert.concert.controller.DTO.PointResponse;
import hhplusconcert.concert.domain.constant.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointHistoryRepository pointHistoryRepository;
    private final PointRepository pointRepository;

    /**
     * 포인트 충전
     * @param pointChargeRequest
     * @return
     */
    @Transactional
    public PointChargeResponse chargePoint(PointChargeRequest pointChargeRequest) {
        Long userId = pointChargeRequest.userId();
        Long chargePoint = pointChargeRequest.chargePoint();

        Point point = pointRepository.findPointByUserId(userId);

        //chargePoint가 음수면 exception
        if(chargePoint < 0) {
            throw new IllegalArgumentException("올바른 포인트가 아닙니다.");
        }

        //포인트가 없으면 0으로 초기화
        if(point.getPoint() == null) {
            point.setPoint(0L);
        }

        point.setPoint(point.getPoint()+chargePoint);
        PointHistory pointHistory = PointHistory.builder()
                .userId(userId)
                .point(chargePoint)
                .transactionType(TransactionType.CHARGE)
                .build();

        pointHistoryRepository.save(PointHistory.toEntity(pointHistory));


        return PointMapper.toChargeResponse(pointRepository.updatePoint(PointMapper.toEntity(point)),chargePoint);
    }

    /**
     * 포인트 조회
     * @param userId
     * @return
     */
    public PointResponse findPoint (Long userId) {
        return PointMapper.toResponse(pointRepository.findPointByUserId(userId));
    }


}
