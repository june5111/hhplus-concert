package hhplusconcert.concert.domain.Point;

import hhplusconcert.concert.domain.constant.TransactionType;
import lombok.*;

import java.util.function.Consumer;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    private Long pointId;
    private Long userId;
    private Long point;


}
