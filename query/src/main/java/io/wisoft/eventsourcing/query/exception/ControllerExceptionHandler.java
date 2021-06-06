package io.wisoft.eventsourcing.query.exception;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<BindingErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    BindingErrorResponse bindingErrorResponse = BindingErrorResponse.builder()
        .title("Binding Exception")
        .message("입력하신 정보가 유효하지 않습니다.")
        .build();
    bindingErrorResponse.setErrors(e.getBindingResult().getFieldErrors());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingErrorResponse);

  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException e) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .title("Page Not Found")
        .message("해당 페이지를 발견할 수 없습니다.")
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(ActuatingNotFoundException.class)
  public ResponseEntity<ErrorResponse> actuatingNotFoundExceptionHandler(ActuatingNotFoundException e) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .title("ActuatingNotFoundException")
        .message(e.getActuatingId() + "에 해당하는 데이터가 존재하지 않습니다.")
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(SensingNotFoundException.class)
  public ResponseEntity<ErrorResponse> sensingNotFoundExceptionHandler(SensingNotFoundException e) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .title("SensingNotFoundException")
        .message(e.getSensingId() + "에 해당하는 데이터가 존재하지 않습니다.")
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

}
