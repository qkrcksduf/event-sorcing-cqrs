package io.wisoft.iotplatform.device.domain;

import device.ValidationStatus;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
//import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@AllArgsConstructor
@ToString
@Data
public class ActuatorValidationCheckCommand {

    @TargetAggregateIdentifier
    private UUID actuatingId;

    private UUID actuatorId;

    private UUID deviceId;

    private UUID modelId;

    private String actuatorName;

    private String deviceName;

    private ValidationStatus actuatorValidation;

    private String description;

    private String actuatingValue;

    public ActuatorValidationCheckCommand(UUID actuatingId, UUID actuatorId, ValidationStatus actuatorStatus, String actuatingValue, String description) {
        this.actuatingId = actuatingId;
        this.actuatorId = actuatorId;
        this.actuatorValidation = actuatorStatus;
        this.actuatingValue = actuatingValue;
        this.description = description;
    }

}
