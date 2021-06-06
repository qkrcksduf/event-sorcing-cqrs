package io.wisoft.eventsourcing.sensing.service;

import io.wisoft.eventsourcing.sensing.domain.AutoControlDetectCommand;
import io.wisoft.eventsourcing.sensing.exception.ActuatorNotFoundException;
import io.wisoft.eventsourcing.sensing.exception.SensorNotFoundException;
import io.wisoft.eventsourcing.sensing.ui.SensingDto.SensingRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensingService {

  @Value("${multiplicationHost}")
  private String API_GATEWAY_HOST;
  private final RestTemplate restTemplate;
  private final CommandGateway gateway;

  public CompletableFuture<Object> register(final SensingRegister sensingDto) {
    log.debug("handling {}", sensingDto);
    checkExistSensor(sensingDto.getSensorId());
//    String actuatorName = checkExistActuator(sensingDto.getActuatorId());

    return gateway.send(new AutoControlDetectCommand(
        UUID.randomUUID(),
        sensingDto.getSensorId(),
        sensingDto.getActuatorId(),
        "서보모터",
        sensingDto.getActuatingValue(),
        LocalDateTime.now(),
        sensingDto.getSensingValue(),
        sensingDto.getEnvironmentValue()
    ));
  }

  private void checkExistSensor(final UUID sensorId) {
    try {
      System.out.println("API_GATEWAY_HOST" + API_GATEWAY_HOST);
      restTemplate.getForEntity(API_GATEWAY_HOST + "/sensors/{id}", Object.class, sensorId);
    } catch (HttpClientErrorException e) {
      throw new SensorNotFoundException(String.valueOf(sensorId));
    }
  }

  private String checkExistActuator(final UUID actuatorId) {
    try {
      final ResponseEntity<Object> response = restTemplate.getForEntity(API_GATEWAY_HOST + "/actuators/{id}", Object.class, actuatorId);
      Map<String, Object> actuator = (Map<String, Object>) response.getBody();
      if (actuator != null && actuator.isEmpty()) {
        throw new ActuatorNotFoundException(String.valueOf(actuatorId));
      }
      return String.valueOf(actuator.get("name"));

    } catch (HttpClientErrorException e) {
      throw new ActuatorNotFoundException(String.valueOf(actuatorId));
    }
  }

}
