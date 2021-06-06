package io.wisoft.eventsourcing.actuating.service;

import io.wisoft.eventsourcing.actuating.domain.ActuatingResult;
import io.wisoft.eventsourcing.actuating.exception.ActuatorNotFoundException;
import io.wisoft.eventsourcing.actuating.ui.ActuatingResultDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActuatingServiceTest {

  @InjectMocks
  private ActuatingService service;

  @Mock
  private RestTemplate template;

  @Mock
  private CommandGateway gateway;

  public ActuatingServiceTest() {
  }

  @Test
  @DisplayName("ActuatingResult 저장 성공")
  public void givenActuatingDtoWhenCallSaveActuatingResultThenReturnCompletableFuture() throws ExecutionException, InterruptedException {

    // given
    ActuatingResultDto actuatingResultDto = createActuatingResultDto();
    actuatingResultDto.setActuatingResult(ActuatingResult.SUCCESS);
    String expectedValue = actuatingResultDto.getActuatingId().toString();
    CompletableFuture<String> expectedValueToCompletableFuture = CompletableFuture.completedFuture(expectedValue);

    // when
    when(service.saveActuatingResult(actuatingResultDto)).thenReturn(expectedValueToCompletableFuture);

    // then
    assertEquals(expectedValue, service.saveActuatingResult(actuatingResultDto).get());
  }

  @Test
  @DisplayName("ActuatingResult 저장 실패")
  public void givenActuatingDtoWhenCallSaveActuatingResultThenReturnActuatorNotFoundException() throws ExecutionException, InterruptedException {
    service = mock(ActuatingService.class);

    // given
    ActuatingResultDto actuatingResultDto = createActuatingResultDto();
    actuatingResultDto.setActuatingResult(ActuatingResult.SUCCESS);

    // when
    willThrow(ActuatorNotFoundException.class)
        .given(service)
        .checkExistActuator(actuatingResultDto.getActuatorId());
    // then
    assertThrows(ActuatorNotFoundException.class, () -> service.checkExistActuator(actuatingResultDto.getActuatorId()));
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