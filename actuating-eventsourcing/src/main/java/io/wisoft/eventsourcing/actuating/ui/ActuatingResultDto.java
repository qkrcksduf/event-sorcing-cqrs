package io.wisoft.eventsourcing.actuating.ui;

import io.wisoft.eventsourcing.actuating.domain.ActuatingResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActuatingResultDto {

  @NotNull
  private UUID actuatingId;

  @NotNull
  private UUID actuatorId;

  @NotNull
  private String actuatorName;

  @NotNull
  private String actuatingValue;

  @NotNull
  private ActuatingResult actuatingResult;

  private String description;

}
