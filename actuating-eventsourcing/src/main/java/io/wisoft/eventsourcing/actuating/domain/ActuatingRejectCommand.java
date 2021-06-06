package io.wisoft.eventsourcing.actuating.domain;

import device.ValidationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ActuatingRejectCommand {

  @AggregateIdentifier
  private UUID actuatingId;

  private UUID actuatorId;

  private ValidationStatus actuatorValidation;

  private String actuatingValue;

  private String description;

}
