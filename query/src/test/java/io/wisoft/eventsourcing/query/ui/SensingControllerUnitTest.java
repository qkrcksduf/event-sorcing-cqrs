package io.wisoft.eventsourcing.query.ui;

import io.wisoft.eventsourcing.query.domain.Sensing;
import io.wisoft.eventsourcing.query.service.SensingService;
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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensingControllerUnitTest {

  @InjectMocks
  SensingController controller;

  @Mock
  SensingService service;

  @Test
  @DisplayName("전체 Sensing 조회에 대해 성공")
  public void findSensingAllTest() {
    //given
    Sensing sensing = createSensing();
    List<Sensing> sensingList = new ArrayList<>();
    sensingList.add(sensing);

    //when
    when(service.findSensingAll()).thenReturn(sensingList);
    ResponseEntity<List<Sensing>> result = controller.findSensingAll();

    //then
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(sensing.getSensingId(), result.getBody().get(0).getSensingId());
    assertEquals(sensing.getSensorId(), result.getBody().get(0).getSensorId());
    assertEquals(sensing.getSensingTime(), result.getBody().get(0).getSensingTime());
    assertEquals(sensing.getSensingValue(), result.getBody().get(0).getSensingValue());
    assertEquals(sensing.getEnvironmentValue(), result.getBody().get(0).getEnvironmentValue());
  }

  @Test
  @DisplayName("특정 Sensor의 Sensing값 조회")
  public void findSensingBySensorId() {
    //given
    Sensing sensing = createSensing();
    List<Sensing> sensingList = new ArrayList<>();
    sensingList.add(sensing);

    //when
    when(service.findSensingBySensorId(sensing.getSensorId())).thenReturn(sensingList);
//    ResponseEntity<List<Sensing>> result = controller.findSensingBySensorId(sensing.getSensorId());

    //then
//    assertEquals(HttpStatus.OK, result.getStatusCode());
//    assertEquals(sensing.getSensingId(), result.getBody().get(0).getSensingId());
//    assertEquals(sensing.getSensorId(), result.getBody().get(0).getSensorId());
//    assertEquals(sensing.getSensingTime(), result.getBody().get(0).getSensingTime());
//    assertEquals(sensing.getSensingValue(), result.getBody().get(0).getSensingValue());
//    assertEquals(sensing.getEnvironmentValue(), result.getBody().get(0).getEnvironmentValue());
  }

  private Sensing createSensing() {
    HashMap<String, String> sensingValue = new HashMap<>();
    HashMap<String, String> environmentValue = new HashMap<>();
    sensingValue.put("dustValue",  "40.0");
    environmentValue.put("temperature", "30.0");


    return Sensing.builder()
        .sensingId(UUID.randomUUID())
        .sensorId(UUID.randomUUID())
        .sensingValue(sensingValue)
        .environmentValue(environmentValue)
        .sensingTime(LocalDateTime.now())
        .build();
  }

}