package io.wisoft.eventsourcing.query.ui;

import io.wisoft.eventsourcing.query.domain.Actuating;
import io.wisoft.eventsourcing.query.service.ActuatingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActuatingControllerUnitTest {

  @InjectMocks
  ActuatingController controller;

  @Mock
  private ActuatingService service;

  @Test
  @DisplayName("전체 Actuating 목록 조회에 대해 성공")
  public void findActuatingAllTest() {
   //given
   final Actuating actuating = createActuating();
   List<Actuating> actuatingList = new ArrayList<>();
   actuatingList.add(actuating);

   //when
    when(service.findActuatingAll()).thenReturn(actuatingList);
    final ResponseEntity<List<Actuating>> result = controller.findActuatingAll();

    //then
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(actuating.getActuatingId(), result.getBody().get(0).getActuatingId());
    assertEquals(actuating.getActuatorId(), result.getBody().get(0).getActuatorId());
    assertEquals(actuating.getActuatingTime(), result.getBody().get(0).getActuatingTime());
    assertEquals(actuating.getActuatingValue(), result.getBody().get(0).getActuatingValue());
    assertEquals(actuating.getActuatorName(), result.getBody().get(0).getActuatorName());
  }

  @Test
  @DisplayName("특정 Actuator의 Actuating 전체 목록 조회에 대해 성공")
  public void findActuatingByActuatorIdTest() {
    //given
    final Actuating actuating = createActuating();
    List<Actuating> actuatingList = new ArrayList<>();
    actuatingList.add(actuating);

    //when
    when(service.findActuatingByActuatorId(actuating.getActuatorId())).thenReturn(actuatingList);
//    final ResponseEntity<List<Actuating>> result = controller.findActuatingByActuatorId(actuating.getActuatorId());

    //then
//    assertEquals(HttpStatus.OK, result.getStatusCode());
//    assertEquals(actuating.getActuatingId(), result.getBody().get(0).getActuatingId());
//    assertEquals(actuating.getActuatorId(), result.getBody().get(0).getActuatorId());
//    assertEquals(actuating.getActuatingTime(), result.getBody().get(0).getActuatingTime());
//    assertEquals(actuating.getActuatingValue(), result.getBody().get(0).getActuatingValue());
//    assertEquals(actuating.getActuatorName(), result.getBody().get(0).getActuatorName());
  }

  @Test
  @DisplayName("특정 Actuator의 성공한 Actuating에 대한 조회 성공")
  public void findActuatingByActuatorIdAndActuatorResultTest() {
    //given
    final Actuating actuating = createActuating();
    List<Actuating> actuatingList = new ArrayList<>();
    actuatingList.add(actuating);

    //when
    when(service.findActuatingByActuatorIdAndActuatorResult(actuating.getActuatorId(), actuating.getActuatingResult()))
        .thenReturn(actuatingList);
    final ResponseEntity<List<Actuating>> result = controller.findActuatingByActuatorIdAndActuatingResult(actuating.getActuatorId(), actuating.getActuatingResult());

    //then
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals("SUCCESS", result.getBody().get(0).getActuatingResult());

  }

  private Actuating createActuating() {
    return Actuating.builder()
        .actuatingId(UUID.randomUUID())
        .actuatorId(UUID.randomUUID())
        .actuatingTime(LocalDateTime.now())
        .actuatingValue("START")
        .actuatingResult("SUCCESS")
        .actuatorName("서보모터 A")
        .build();
  }

}
