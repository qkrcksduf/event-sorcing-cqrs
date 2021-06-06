package io.wisoft.eventsourcing.query.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class ActuatingNotFoundException extends RuntimeException {

  private UUID actuatingId;

}
