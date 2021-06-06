package device;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ActuatorValidationCheckedUnSuccessfulEvent {

  private UUID actuatingId;

  private UUID actuatorId;

  private String actuatingValue;

  private ValidationStatus actuatorValidation;

  private String description;

}
