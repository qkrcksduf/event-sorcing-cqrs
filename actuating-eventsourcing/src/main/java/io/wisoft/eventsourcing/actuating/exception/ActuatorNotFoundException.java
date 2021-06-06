package io.wisoft.eventsourcing.actuating.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
public class ActuatorNotFoundException extends RuntimeException {

  @Getter
  final String information;

}
