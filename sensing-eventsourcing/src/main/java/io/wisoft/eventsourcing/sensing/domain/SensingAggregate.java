package io.wisoft.eventsourcing.sensing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import sensing.AutoControlDetectedEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity(name = "sensing_event_sourcing")
@Table(name = "sensing_event_sourcing")
@Slf4j
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)
@Aggregate
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SensingAggregate {

  @AggregateIdentifier
  @Id
  @Column(name = "sensing_id")
  private UUID sensingId;

  @Column(name = "sensor_id")
  private UUID sensorId;

  @CreationTimestamp
  @Column(name = "sensing_time")
  private LocalDateTime sensingTime;

  @Type(type = "jsonb")
  @Column(name = "sensing_value", columnDefinition = "jsonb")
  private HashMap<String, String> sensingValue;

  @Type(type = "jsonb")
  @Column(name = "environment_value", columnDefinition = "jsonb")
  private HashMap<String, String> environmentValue;

  @CommandHandler
  public SensingAggregate(final AutoControlDetectCommand command) {
    log.debug("handling {}", command);

    this.sensingId = command.getSensingId();
    this.sensorId = command.getSensorId();
    this.sensingValue = command.getSensingValue();
    this.sensingTime = command.getSensingTime();
    this.environmentValue = command.getEnvironmentValue();

    apply(new AutoControlDetectedEvent(
        command.getSensingId(),
        command.getSensorId(),
        command.getActuatorId(),
        command.getActuatorName(),
        command.getActuatingValue(),
        command.getSensingTime(),
        command.getSensingValue(),
        command.getEnvironmentValue()
    ));
  }

//  @EventSourcingHandler
//  public void on(AutoControlDetectedEvent event) {
//    this.sensingId = event.getSensingId();
//    this.sensorId = event.getSensorId();
//    this.sensingValue = event.getSensingValue();
//    this.sensingTime = event.getSensingTime();
//    this.environmentValue = event.getEnvironmentValue();
//  }
}
