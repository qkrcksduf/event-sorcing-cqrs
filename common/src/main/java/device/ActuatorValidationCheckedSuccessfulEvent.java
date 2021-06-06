package device;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ActuatorValidationCheckedSuccessfulEvent {

  private UUID actuatingId;

  private UUID actuatorId;

  private UUID deviceId;

  private UUID modelId;

  private String deviceName;

  private String actuatingValue;

  private ValidationStatus actuatorValidation;

  private String description;

  private String actuatorName;

}
