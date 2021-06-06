package io.wisoft.iotplatform.device.domain;

import device.ActuatorValidationCheckedSuccessfulEvent;
import device.ActuatorValidationCheckedUnSuccessfulEvent;
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
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ActuatorAggregate {

  @AggregateIdentifier
  private UUID actuatingId;

  @CommandHandler
  public ActuatorAggregate(final ActuatorValidationCheckCommand command) {
    log.debug("handling {}", command);

    this.actuatingId = command.getActuatingId();

    if (command.getActuatorValidation().equals(ValidationStatus.VALID)) {
      apply(new ActuatorValidationCheckedSuccessfulEvent(
          command.getActuatingId(),
          command.getActuatorId(),
          command.getDeviceId(),
          command.getModelId(),
          command.getDeviceName(),
          command.getActuatingValue(),
          command.getActuatorValidation(),
          command.getDescription(),
          command.getActuatorName()
      ));
    } else {
      apply(new ActuatorValidationCheckedUnSuccessfulEvent(
          command.getActuatingId(),
          command.getActuatorId(),
          command.getActuatingValue(),
          command.getActuatorValidation(),
          command.getDescription()
      ));
    }
  }

}
