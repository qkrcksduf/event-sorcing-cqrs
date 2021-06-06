package io.wisoft.eventsourcing.query.ui;

import io.wisoft.eventsourcing.query.domain.Sensing;
import io.wisoft.eventsourcing.query.exception.SensingNotFoundException;
import io.wisoft.eventsourcing.query.service.SensingService;
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
@RequestMapping("/event-querys-sensings")
public class SensingController {

  private final SensingService service;

  @GetMapping
  public ResponseEntity<List<Sensing>> findSensingAll() {
    return ResponseEntity.ok().body(service.findSensingAll());
  }

//  @GetMapping("/{sensorId}")
//  public ResponseEntity<List<Sensing>> findSensingBySensorId(@PathVariable("sensorId") UUID sensorId) {
//    return ResponseEntity.ok().body(service.findSensingBySensorId(sensorId));
//  }

  @GetMapping("{sensingId}")
  public ResponseEntity<Sensing> findSensingBySensingId(@PathVariable("sensingId") UUID sensingId) {
    Sensing findSensing = service.findSensingBySensingId(sensingId);
    if (findSensing == null) throw new SensingNotFoundException(sensingId);
    return ResponseEntity.ok().body(findSensing);
  }


}
