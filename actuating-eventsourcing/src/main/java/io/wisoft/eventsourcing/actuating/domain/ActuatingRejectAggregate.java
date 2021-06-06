package io.wisoft.eventsourcing.actuating.domain;

import actuating.ActuatingRejectedEvent;
import device.ValidationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Getter
public class ActuatingRejectAggregate {

  @AggregateIdentifier
  private UUID actuatingId;

  @CommandHandler
  public ActuatingRejectAggregate(final ActuatingRejectCommand command) {
    log.debug("handling {}", command);
    this.actuatingId = command.getActuatingId();

    apply(
        new ActuatingRejectedEvent(
            command.getActuatingId(),
            command.getActuatorId(),
            command.getActuatorValidation(),
            command.getActuatingValue(),
            command.getDescription()
        )
    );
  }
}
