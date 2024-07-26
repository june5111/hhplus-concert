package hhplusconcert.concert;

import hhplusconcert.concert.controller.DTO.PointChargeRequest;
import hhplusconcert.concert.controller.DTO.PointChargeResponse;
import hhplusconcert.concert.controller.DTO.PointResponse;
import hhplusconcert.concert.domain.Point.*;
import hhplusconcert.concert.infrastructure.point.PointEntity;
import hhplusconcert.concert.infrastructure.point.PointHistoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
public class PointServiceTest {

    @InjectMocks
    private PointService pointService;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @Mock
    private PointRepository pointRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChargePoint_Success() {
        // Given
        PointChargeRequest request = new PointChargeRequest(1L, 100L);
        Point point = new Point();
        point.setUserId(1L);
        point.setPoint(0L);

        when(pointRepository.findPointByUserId(1L)).thenReturn(point);
        when(pointRepository.updatePoint(any(PointEntity.class))).thenReturn(PointMapper.toEntity(point));
       // when(pointHistoryRepository.save(any(PointHistoryEntity.class))).thenReturn(PointMapper.toEntity(new PointHistory()));

        // When
        PointChargeResponse response = pointService.chargePoint(request);

        // Then
        assertNotNull(response);
        assertEquals(100L, response.chargePoint());
        verify(pointRepository, times(1)).findPointByUserId(1L);
        verify(pointRepository, times(1)).updatePoint(any(PointEntity.class));
        verify(pointHistoryRepository, times(1)).save(any(PointHistoryEntity.class));
    }

    private void assertEquals(long l, Long aLong) {
    }

    @Test
    void testChargePoint_InvalidCharge() {
        // Given
        PointChargeRequest request = new PointChargeRequest(1L, -100L);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pointService.chargePoint(request);
        });

        assertEquals("올바른 포인트가 아닙니다.", exception.getMessage());
        verify(pointRepository, never()).findPointByUserId(1L);
        verify(pointRepository, never()).updatePoint(any(PointEntity.class));
        verify(pointHistoryRepository, never()).save(any(PointHistoryEntity.class));
    }

    private void assertEquals(String s, String message) {


    }

    @Test
    void testFindPoint_Success() {
        // Given
        Point point = new Point();
        point.setUserId(1L);
        point.setPoint(100L);

        when(pointRepository.findPointByUserId(1L)).thenReturn(point);

        // When
        PointResponse response = pointService.findPoint(1L);

        // Then
        assertNotNull(response);
        assertEquals(100L, response.point());
        verify(pointRepository, times(1)).findPointByUserId(1L);
    }

}
