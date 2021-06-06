package io.wisoft.eventsourcing.query.projection;

import actuating.ActuatingApprovedEvent;
import actuating.DeviceNotOperatedEvent;
import io.wisoft.eventsourcing.query.domain.Actuating;
import io.wisoft.eventsourcing.query.domain.Sensing;
import io.wisoft.eventsourcing.query.infrastrcture.ActuatingRepository;
import io.wisoft.eventsourcing.query.infrastrcture.SensingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sensing.AutoControlDetectedEvent;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class QueryProjection {

  private final SensingRepository sensingRepository;
  private final ActuatingRepository actuatingRepository;

  @EventHandler
  protected void on(AutoControlDetectedEvent event, @Timestamp Instant instant) {
    log.debug("projecting {}, timestamp {}", event, instant);
    Sensing sensing = Sensing.builder()
        .sensingId(event.getSensingId())
        .sensorId(event.getSensorId())
        .sensingTime(event.getSensingTime())
        .sensingValue(event.getSensingValue())
        .environmentValue(event.getEnvironmentValue())
        .build();
    sensingRepository.saveSensing(sensing);

  }

  @EventHandler
  protected void on(ActuatingApprovedEvent event, @Timestamp Instant instant) {
    log.debug("projecting {}, timestamp {}", event, instant);
    Actuating actuating = Actuating.builder()
        .actuatingId(event.getActuatingId())
        .actuatorId(event.getActuatorId())
        .actuatorName(event.getActuatorName())
        .actuatingTime(event.getActuatingTime())
        .actuatingValue(event.getActuatingValue())
        .actuatingResult(event.getActuatingResult())
        .build();
    actuatingRepository.saveActuating(actuating);
  }

  @EventHandler
  protected void on(DeviceNotOperatedEvent event, @Timestamp Instant instant) {
    log.debug("projecting {}, timestamp {}", event, instant);
    Actuating actuating = Actuating.builder()
        .actuatingId(event.getActuatingId())
        .actuatorId(event.getActuatorId())
        .actuatorName(event.getActuatorName())
        .actuatingValue(event.getActuatingValue())
        .actuatingTime(event.getActuatingTime())
        .actuatingResult(event.getActuatingResult())
        .description(event.getDescription())
        .build();
    actuatingRepository.saveActuating(actuating);
  }
}
