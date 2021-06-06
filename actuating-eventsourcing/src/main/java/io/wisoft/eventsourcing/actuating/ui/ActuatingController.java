package io.wisoft.eventsourcing.actuating.ui;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.wisoft.eventsourcing.actuating.service.ActuatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-actuatings")
@Api(value = "actuating event sourcing")
public class ActuatingController {

  private final ActuatingService actuatingService;

  @ApiOperation(value = "액추에이팅 결과 등록", notes = "시스템에 액추에이팅 결과를 등록합니다.")
  @ApiResponses({
      @ApiResponse(code = 201, message="CREATED")
  })
  @PostMapping("/result")
  public ResponseEntity<CompletableFuture<String>> saveActuatingResult(@RequestBody @Valid final ActuatingResultDto actuatingResultDto) {
    System.out.println("들어옴");
    return ResponseEntity.status(CREATED).body(actuatingService.saveActuatingResult(actuatingResultDto));
  }

}
