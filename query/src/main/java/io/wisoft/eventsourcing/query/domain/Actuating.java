package io.wisoft.eventsourcing.query.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Actuating {

  @Id
  @Column(name = "actuating_id")
  private UUID actuatingId;

  @Column(name = "actuator_id")
  private UUID actuatorId;

  @Column(name = "actuator_name")
  private String actuatorName;

  @Column(name = "actuating_value")
  private String actuatingValue;

  @Column(name = "actuating_time")
  private LocalDateTime actuatingTime;

  @Column(name = "result")
  private String actuatingResult;

  @Column(name = "description")
  private String description;

}
