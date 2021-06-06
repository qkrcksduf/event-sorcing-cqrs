package io.wisoft.eventsourcing.actuating.ui;

import io.wisoft.eventsourcing.actuating.exception.ActuatorNotFoundException;
import io.wisoft.eventsourcing.actuating.service.ActuatingService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActuatingControllerUnitTest {

  ActuatingController controller;

  @InjectMocks
  ActuatingService service;

  @Mock
  RestTemplate template;

  @Mock
  CommandGateway gateway;


  @BeforeEach()
  public void setup() {
    this.controller = new ActuatingController(service);
  }

  @Test
  @DisplayName("Actuator 제어 후 결과저장에 대해 성공")
  public void givenActuatingResultDtoWhenCallSaveActuatingResultThenCreated() throws Exception {

    //given
    ActuatingResultDto actuatingResultDto = createActuatingResultDto();
    String expected = actuatingResultDto.getActuatingId().toString();
    CompletableFuture<String> serviceResult = CompletableFuture.completedFuture(expected);

    //when
    when(service.saveActuatingResult(actuatingResultDto)).thenReturn(serviceResult);

    ResponseEntity<CompletableFuture<String>> result = controller.saveActuatingResult(actuatingResultDto);

    //then
    assertEquals(HttpStatus.CREATED, result.getStatusCode());
    assertEquals(expected, result.getBody().get());
  }

  @Test
  @DisplayName("Actuator 제어 후 결과저장에 대해 실패")
  public void givenActuatingResultDtoWhenCallSaveActuatingResultThenActuatorNotFoundException() throws Exception {

    //given
    ActuatingResultDto actuatingResultDto = createActuatingResultDto();

    //when
    when(service.saveActuatingResult(actuatingResultDto)).thenThrow(ActuatorNotFoundException.class);

    //then
    assertThrows(ActuatorNotFoundException.class, () -> controller.saveActuatingResult(actuatingResultDto));
  }

  private ActuatingResultDto createActuatingResultDto() {
    final UUID actuatingId = UUID.randomUUID();
    final UUID actuatorId = UUID.fromString("3ca19abd-8ac3-44a3-a169-412529b1b42b");
    final String actuatingValue = "START";
    final String actuatorName = "서보모터 A";

    return ActuatingResultDto.builder()
        .actuatingId(actuatingId)
        .actuatorId(actuatorId)
        .actuatorName(actuatorName)
        .actuatingValue(actuatingValue)
        .build();
  }

}
