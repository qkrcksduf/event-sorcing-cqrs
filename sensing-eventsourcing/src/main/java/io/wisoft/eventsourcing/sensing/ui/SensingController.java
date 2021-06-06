package io.wisoft.eventsourcing.sensing.ui;

import io.wisoft.eventsourcing.sensing.service.SensingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.wisoft.eventsourcing.sensing.ui.SensingDto.SensingRegister;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/event-sensings")
@RequiredArgsConstructor
public class SensingController {

  private final SensingService sensingService;

  @PostMapping("auto")
  public ResponseEntity<CompletableFuture<Object>> createSensing(@RequestBody @Valid final SensingRegister registerDto) {
    System.out.println(registerDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(sensingService.register(registerDto));
  }
}
