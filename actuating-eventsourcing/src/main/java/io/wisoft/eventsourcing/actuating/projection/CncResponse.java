package io.wisoft.eventsourcing.actuating.projection;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CncResponse {

  private UUID id;
  private String serial;
  private String name;
  private String location;
  private String status;
  private UUID groupId;
  private String ipAddress;
  private LocalDateTime joined;
  private LocalDateTime updated;
  private String description;

}
