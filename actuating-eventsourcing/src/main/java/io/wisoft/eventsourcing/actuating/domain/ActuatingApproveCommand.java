package io.wisoft.eventsourcing.actuating.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ActuatingApproveCommand {

  @TargetAggregateIdentifier
  private UUID actuatingId;
  private UUID actuatorId;
  private String actuatorName;
  private String actuatingValue;
  private ActuatingResult actuatingResult;

}
