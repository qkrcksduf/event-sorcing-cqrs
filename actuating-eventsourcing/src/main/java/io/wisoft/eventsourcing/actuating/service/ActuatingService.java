package io.wisoft.eventsourcing.actuating.service;

import io.wisoft.eventsourcing.actuating.domain.ActuatingApproveCommand;
import io.wisoft.eventsourcing.actuating.domain.DeviceNotOperateCommand;
import io.wisoft.eventsourcing.actuating.exception.ActuatorNotFoundException;
import io.wisoft.eventsourcing.actuating.ui.ActuatingResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static io.wisoft.eventsourcing.actuating.domain.ActuatingResult.FAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActuatingService {

  @Value("${multiplicationHost}")
  private String API_GATEWAY_HOST;
  private final CommandGateway gateway;
  private final RestTemplate restTemplate;

  public CompletableFuture<String> saveActuatingResult(final ActuatingResultDto actuatingResultDto) {
    log.debug("handling {}", actuatingResultDto);
    checkExistActuator(actuatingResultDto.getActuatorId());

    if (actuatingResultDto.getActuatingResult() == FAIL) {
      return gateway.send(
          new DeviceNotOperateCommand(
              actuatingResultDto.getActuatingId(),
              actuatingResultDto.getActuatorId(),
              actuatingResultDto.getActuatorName(),
              actuatingResultDto.getActuatingValue(),
              actuatingResultDto.getDescription(),
              actuatingResultDto.getActuatingResult()
          )
      );
    }

    return gateway.send(
        new ActuatingApproveCommand(
            actuatingResultDto.getActuatingId(),
            actuatingResultDto.getActuatorId(),
            actuatingResultDto.getActuatorName(),
            actuatingResultDto.getActuatingValue(),
            actuatingResultDto.getActuatingResult()
        )
    );
  }

  protected void checkExistActuator(final UUID actuatorId) {
    try {
      System.out.println("check");
      restTemplate.getForEntity(API_GATEWAY_HOST + "/actuators/{id}", Object.class, actuatorId);
    } catch (HttpClientErrorException e) {
      throw new ActuatorNotFoundException(String.valueOf(actuatorId));
    }
  }

}
