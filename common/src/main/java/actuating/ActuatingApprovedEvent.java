package actuating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
public class ActuatingApprovedEvent {

  private UUID actuatingId;

  private UUID actuatorId;

  private String actuatorName;

  private LocalDateTime actuatingTime;

  private String actuatingValue;

  private String actuatingResult;

}