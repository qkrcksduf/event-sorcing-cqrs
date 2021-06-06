package io.wisoft.eventsourcing.actuating.projection;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class ActuatingDto {

  private UUID actuatingId;
  private UUID actuatorId;
  private String actuatorName;
  private String actuatingValue;
  private String protocol;

}
