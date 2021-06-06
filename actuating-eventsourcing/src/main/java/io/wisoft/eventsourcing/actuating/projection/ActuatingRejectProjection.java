package io.wisoft.eventsourcing.actuating.projection;

import device.ActuatorValidationCheckedSuccessfulEvent;
import device.ActuatorValidationCheckedUnSuccessfulEvent;
import io.wisoft.eventsourcing.actuating.domain.ActuatingRejectCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ActuatingRejectProjection {

  private final CommandGateway gateway;


//  @EventHandler
//  protected void rejectActuating(ActuatorValidationCheckedUnSuccessfulEvent event, @Timestamp Instant instant) {
//    log.debug("projecting {}, timestamp {}", event, instant);
//    gateway.send(new ActuatingRejectCommand(
//        event.getActuatingId(),
//        event.getActuatorId(),
//        event.getActuatorValidation(),
//        event.getActuatingValue(),
//        event.getDescription()));
//  }

  @EventHandler
  protected void actuating(ActuatorValidationCheckedUnSuccessfulEvent event, @Timestamp Instant instant) {
    log.debug("projecting {}, timestamp {}", event, instant);
    gateway.send(new ActuatingRejectCommand(
        event.getActuatorId(),
        event.getActuatorId(),
        event.getActuatorValidation(),
        event.getActuatingValue(),
        event.getDescription()));
  }
}
