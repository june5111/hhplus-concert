package hhplusconcert.concert.Facade;

import hhplusconcert.concert.controller.DTO.QueueRegisterRequest;
import hhplusconcert.concert.controller.DTO.QueueRegisterResponse;
import hhplusconcert.concert.controller.DTO.QueueStatusResponse;
import hhplusconcert.concert.domain.queue.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueFacade {

    private final QueueService queueService;

    public QueueRegisterResponse register(QueueRegisterRequest registerRequest) {
        return queueService.register(registerRequest);
    }

    public QueueStatusResponse checkNumber(Long waitingId) {
        return queueService.checkWaitingNum(waitingId);
    }




}
