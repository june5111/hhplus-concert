package hhplusconcert.concert.controller;

import hhplusconcert.concert.Facade.QueueFacade;
import hhplusconcert.concert.controller.DTO.QueueRegisterRequest;
import hhplusconcert.concert.controller.DTO.QueueRegisterResponse;
import hhplusconcert.concert.controller.DTO.QueueStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QueueController {

    private QueueFacade queueFacade;

    @PostMapping("/concert/wait/register")
    public QueueRegisterResponse queueRegister(QueueRegisterRequest registerRequest) {
        return queueFacade.register(registerRequest);
    }

    @GetMapping("/concert/wait/status/{waitingId}")
    public QueueStatusResponse checkQueue(@PathVariable Long waitingId) {
        return queueFacade.checkNumber(waitingId);
    }

}
