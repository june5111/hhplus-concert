package hhplusconcert.concert.domain.Point;

import hhplusconcert.concert.common.ResponseCode;
import hhplusconcert.concert.controller.DTO.PointChargeResponse;
import hhplusconcert.concert.controller.DTO.PointResponse;
import hhplusconcert.concert.infrastructure.point.PointEntity;

public class PointMapper {

    public static Point toDomain(PointEntity pointEntity) {
        return Point.builder()
                .pointId(pointEntity.getPointId())
                .userId(pointEntity.getUserId())
                .point(pointEntity.getPoint())
                .build();
    }

    public static Point toEntity(Point point) {
        return PointEntity.builder()
                .pointId(point.getPointId())
                .userId(point.getUserId())
                .point(point.getPoint())
                .build();
    }

    public static PointResponse toResponse(Point point) {
        return PointResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .point(point.getPoint())
                .build();
    }

    public static PointChargeResponse toChargeResponse(Point point, Long chargePoint) {
        return PointChargeResponse.builder()
                .responseCode(ResponseCode.SUCCESS)
                .point(point.getPoint())
                .chargePoint(chargePoint)
                .build();
    }



}
