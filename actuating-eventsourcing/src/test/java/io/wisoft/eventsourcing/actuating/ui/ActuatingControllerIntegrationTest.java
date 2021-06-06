package io.wisoft.eventsourcing.actuating.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.eventsourcing.actuating.domain.ActuatingResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ActuatingControllerIntegrationTest {

  @Autowired
  protected MockMvc mvc;

  @Autowired
  protected ObjectMapper objectMapper;

  @Test
  @DisplayName("Actuator 제어 후 결과저장에 대해 성공")
  public void givenActuatingResultDtoWhenCallSaveActuatingResultThenCreated() throws Exception {
    // given
    ActuatingResultDto actuatingResultDto = createActuatingResultDto();
    actuatingResultDto.setActuatingResult(ActuatingResult.SUCCESS);

    // when
    ResultActions resultActions = mvc.perform(post("/event-actuatings/result")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(actuatingResultDto)))
        .andDo(print());

    // then
    resultActions.andExpect(status().isCreated());
  }

  @Test
  @DisplayName("Actuator 제어 후 결과저장에 대해 실패 (validation 문제)")
  public void givenActuatingResultDtoWhenCallSaveActuatingResultThenBadRequest() throws Exception {
    // given
    ActuatingResultDto actuatingResultDto = createActuatingResultDto();
    actuatingResultDto.setActuatingResult(ActuatingResult.SUCCESS);
    actuatingResultDto.setActuatingId(null);
    actuatingResultDto.setActuatorId(null);

    // when
    ResultActions resultActions = mvc.perform(post("/event-actuatings/result")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(actuatingResultDto)))
        .andDo(print());

    // then
    resultActions.andExpect(status().isBadRequest());
  }

  private ActuatingResultDto createActuatingResultDto() {
    final UUID actuatingId = UUID.randomUUID();
    final UUID actuatorId = UUID.fromString("3ca19abd-8ac3-44a3-a169-412529b1b42b");
    final String actuatingValue = "START";
    final String actuatorName = "서보모터 A";

    return ActuatingResultDto.builder()
        .actuatingId(actuatingId)
        .actuatorId(actuatorId)
        .actuatorName(actuatorName)
        .actuatingValue(actuatingValue)
        .build();
  }

}