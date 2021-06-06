package io.wisoft.eventsourcing.query.infrastrcture;

import io.wisoft.eventsourcing.query.domain.Sensing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SensingRepository {

  private final EntityManager entityManager;

  public void saveSensing(Sensing sensing) {
    entityManager.persist(sensing);
  }

  public List<Sensing> findSensingAll() {
    final String query = "SELECT s FROM Sensing s";
    return entityManager.createQuery(query, Sensing.class).getResultList();
  }

  public List<Sensing> findSensingBySensorId(UUID sensorId) {
    final String query = "SELECT s FROM Sensing s WHERE s.sensorId = :sensorId";
    return entityManager.createQuery(query, Sensing.class)
        .setParameter("sensorId", sensorId)
        .getResultList();
  }

  public Sensing findSensingBySensingId(UUID sensingId) {
    final String query = "SELECT s FROM Sensing s WHERE s.sensingId=:sensingId";
    return entityManager.createQuery(query, Sensing.class)
        .setParameter("sensingId", sensingId)
        .getResultList()
        .stream()
        .findFirst()
        .orElse(null);
  }
}
