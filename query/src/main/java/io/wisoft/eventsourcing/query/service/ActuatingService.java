package io.wisoft.eventsourcing.query.service;

import io.wisoft.eventsourcing.query.domain.Actuating;
import io.wisoft.eventsourcing.query.infrastrcture.ActuatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActuatingService {

  private final ActuatingRepository repository;

  public List<Actuating> findActuatingAll() {
    return repository.findActuatingAll();
  }

  public List<Actuating> findActuatingByActuatorId(UUID actuatorId) {
    return repository.findActuatingByActuatorId(actuatorId);
  }

  public List<Actuating> findActuatingByActuatorIdAndActuatorResult(UUID actuatorId, String actuatingResult) {
    return repository.findActuatingByActuatorIdAndActuatorResult(actuatorId, actuatingResult);
  }

  public Actuating findActuatingByActuatingId(UUID actuatingId) {
    return repository.findActuatingByActuatingId(actuatingId);
  }
}
