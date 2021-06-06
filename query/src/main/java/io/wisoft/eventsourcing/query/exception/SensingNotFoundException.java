package io.wisoft.eventsourcing.query.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SensingNotFoundException extends RuntimeException {

  private final UUID sensingId;

}
