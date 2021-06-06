package io.wisoft.eventsourcing.query.service;

import io.wisoft.eventsourcing.query.domain.Sensing;
import io.wisoft.eventsourcing.query.infrastrcture.SensingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensingService {

  private final SensingRepository repository;

  public List<Sensing> findSensingAll() {
    return repository.findSensingAll();
  }

  public List<Sensing> findSensingBySensorId(UUID sensorId) {
    return repository.findSensingBySensorId(sensorId);
  }

  public Sensing findSensingBySensingId(UUID sensingId) {
    return repository.findSensingBySensingId(sensingId);
  }
}
