package io.wisoft.eventsourcing.actuating.projection;

import device.ActuatorValidationCheckedSuccessfulEvent;
import device.ActuatorValidationCheckedUnSuccessfulEvent;
import io.wisoft.eventsourcing.actuating.domain.ActuatingRejectCommand;
import io.wisoft.eventsourcing.actuating.exception.CncNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import sensing.AutoControlDetectedEvent;

import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActuatingProjection {

  @Value("${multiplicationHost}")
  private String API_GATEWAY_HOST;
  private final RestTemplate restTemplate;

  @EventHandler
  protected void actuating(ActuatorValidationCheckedSuccessfulEvent event, @Timestamp Instant instant) {
    log.debug("projecting {}, timestamp {}", event, instant);

    ActuatingDto actuatingDto = ActuatingDto.builder().
        actuatingId(UUID.randomUUID())
        .actuatorId(event.getActuatorId())
        .actuatorName(event.getActuatorName())
        .actuatingValue(event.getActuatingValue())
        .protocol("MQTT")
        .build();

    System.out.print("actuatingDto: ");
    System.out.println(actuatingDto.toString());
//    final String address = getCncIpAddressByActuatorId(event.getActuatorId());
//    final String cncAddress = "http://" + address + ":8010/api/v1/actuators/auto";
//    System.out.println("cncAddress: " + cncAddress);
//    System.out.println("actuatingDto" + actuatingDto);
//    final URI uri = URI.create(cncAddress);
//
    String path = API_GATEWAY_HOST + "/virtual-cncs/async-actuating";
    final URI uri = URI.create(path);
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<ActuatingDto> entity = new HttpEntity<>(actuatingDto, headers);

    try {
      ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
      System.out.println(response);
      System.out.println(response.getStatusCode());

    } catch (HttpClientErrorException e) {
      throw new CncNotFoundException(String.valueOf(actuatingDto.getActuatorId()));
    }
  }

  private String getCncIpAddressByActuatorId(final UUID actuatorId) {
    try {
      final ResponseEntity<CncResponse> response =
          restTemplate.getForEntity(API_GATEWAY_HOST + "/cncs/search/actuator?q={id}", CncResponse.class, actuatorId);
      return response.getBody().getIpAddress();
    } catch (HttpClientErrorException e) {
      throw new CncNotFoundException(String.valueOf(actuatorId));
    }
  }

}
