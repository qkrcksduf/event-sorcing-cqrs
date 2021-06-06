package io.wisoft.eventsourcing.actuating.exception.handler;

import io.wisoft.eventsourcing.actuating.exception.ActuatorNotFoundException;
import io.wisoft.eventsourcing.actuating.exception.CncNotFoundException;
import io.wisoft.eventsourcing.actuating.exception.response.BindingErrorResponse;
import io.wisoft.eventsourcing.actuating.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ActuatingControllerExceptionHandler {

  @ExceptionHandler(ActuatorNotFoundException.class)
  public ResponseEntity<ErrorResponse> actuatorNotFoundExceptionHandler(ActuatorNotFoundException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            ErrorResponse.builder()
                .title("Actuator Not Found Exception")
                .message("입력하신 " + e.getInformation() + "는 존재하지 않는 액추에이터입니다.")
                .build()
        );
  }

  @ExceptionHandler(CncNotFoundException.class)
  public ResponseEntity<ErrorResponse> cncNotFoundExceptionHandler(CncNotFoundException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            ErrorResponse.builder()
                .title("CnC Not Found Exception")
                .message("입력하신 " + e.getInformation() + "에 해당하는 CnC가 존재하지 않습니다.")
                .build()
        );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BindingErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    BindingErrorResponse bindingErrorResponse = BindingErrorResponse.builder()
        .title("Biding Exception")
        .message("입력하신 정보가 유효하지 않습니다.")
        .build();
    bindingErrorResponse.setErrors(e.getBindingResult().getFieldErrors());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingErrorResponse);
  }

}
