package io.wisoft.eventsourcing.actuating.domain;

import actuating.ActuatingApprovedEvent;
import actuating.DeviceNotOperatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Entity
@Table(name = "actuating_event_sourcing")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ActuatingAggregate {

  @Id
  @AggregateIdentifier
  @Column(name = "actuating_id")
  private UUID actuatingId;

  @Column(name = "actuatorId", nullable = false)
  private UUID actuatorId;

  @CreationTimestamp
  @Column(name = "actuating_time", nullable = false)
  private LocalDateTime actuatingTime;

  @Column(name = "actuating_value", nullable = false)
  private String actuatingValue;

  @Column(name = "result")
  private String result;

  @CommandHandler
  public ActuatingAggregate(final ActuatingApproveCommand command) {
    log.debug("handling {}", command);

    this.actuatingId = command.getActuatingId();
    this.actuatorId = command.getActuatorId();
    this.actuatingValue = command.getActuatingValue();
    this.actuatingTime = LocalDateTime.now();
    this.result = command.getActuatingResult().toString();

    apply(new ActuatingApprovedEvent(
        command.getActuatingId(),
        command.getActuatorId(),
        command.getActuatorName(),
        this.actuatingTime,
        command.getActuatingValue(),
        command.getActuatingResult().toString()
    ));
  }

  @CommandHandler
  public ActuatingAggregate(final DeviceNotOperateCommand command) {
    log.debug("handling {}", command);

    this.actuatingId = command.getActuatingId();
    this.actuatorId = command.getActuatorId();
    this.actuatingValue = command.getActuatingValue();
    this.actuatingTime = LocalDateTime.now();
    this.result = command.getActuatingResult().toString();

    apply(new DeviceNotOperatedEvent(
        command.getActuatingId(),
        command.getActuatorId(),
        command.getActuatorName(),
        command.getActuatingValue(),
        this.actuatingTime,
        command.getActuatingResult().toString(),
        command.getDescription()
    ));
  }

}
