package io.wisoft.eventsourcing.query.ui;

import io.wisoft.eventsourcing.query.domain.Actuating;
import io.wisoft.eventsourcing.query.exception.ActuatingNotFoundException;
import io.wisoft.eventsourcing.query.service.ActuatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-querys-actuatings")
public class ActuatingController {

  private final ActuatingService service;

  @GetMapping
  public ResponseEntity<List<Actuating>> findActuatingAll() {
    return ResponseEntity.ok().body(service.findActuatingAll());
  }

//  @GetMapping("/{actuatorId}")
//  public ResponseEntity<List<Actuating>> findActuatingByActuatorId(@PathVariable("actuatorId") UUID actuatorId) {
//    return ResponseEntity.ok().body(service.findActuatingByActuatorId(actuatorId));
//  }

  @GetMapping("/{actuatingId}")
  public ResponseEntity<Actuating> findActuatingByActuatingId(@PathVariable("actuatingId") UUID actuatingId) {
    Actuating findActuating = service.findActuatingByActuatingId(actuatingId);
    if (findActuating == null) throw new ActuatingNotFoundException(actuatingId);
    return ResponseEntity.ok().body(findActuating);
  }

  @GetMapping("/{actuatorId}/results/{result}")
  public ResponseEntity<List<Actuating>> findActuatingByActuatorIdAndActuatingResult(@PathVariable("actuatorId") UUID actuatorId, @PathVariable("result") String actuatingResult) {
    return ResponseEntity.ok().body(service.findActuatingByActuatorIdAndActuatorResult(actuatorId, actuatingResult));
  }


}
