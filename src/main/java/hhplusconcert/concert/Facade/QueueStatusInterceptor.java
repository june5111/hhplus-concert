package hhplusconcert.concert.Facade;

import hhplusconcert.concert.domain.queue.QueueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class QueueStatusInterceptor implements HandlerInterceptor {

    private final QueueService queueService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {

        String waitingId = request.getHeader("Authorization");

        if(waitingId == null) {
            throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
        }

        boolean isOngoing = queueService.isOngoingQueue(Long.valueOf(waitingId));

        if(!isOngoing) {
            throw new IllegalArgumentException("대기열 진입 상태가 아닙니다.");
        }

        return HandlerInterceptor.super.preHandle(request,response,handler);
    }


}
