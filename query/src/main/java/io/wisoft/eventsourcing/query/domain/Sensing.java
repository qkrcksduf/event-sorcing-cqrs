package io.wisoft.eventsourcing.query.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@TypeDef(
    name = "jsonb",
    typeClass = JsonBinaryType.class
)
public class Sensing {

  @Id
  @Column(name = "sensing_id")
  private UUID sensingId;

  @Column(name = "sensor_id")
  private UUID sensorId;

  @Column(name = "sensing_time")
  private LocalDateTime sensingTime;

  @Type(type = "jsonb")
  @Column(name = "sensing_value", columnDefinition = "jsonb")
  private HashMap<String, String> sensingValue;

  @Type(type = "jsonb")
  @Column(name = "environment_value", columnDefinition = "jsonb")
  private HashMap<String, String> environmentValue;

}
