package actuating;

import device.ValidationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class ActuatingRejectedEvent {

  private UUID actuatingId;

  private UUID actuatorId;

  private ValidationStatus actuatorValidation;

  private String actuatingValue;

  private String description;

}
