package io.wisoft.eventsourcing.query.infrastrcture;

import io.wisoft.eventsourcing.query.domain.Actuating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ActuatingRepository {

  private final EntityManager entityManager;

  public void saveActuating(final Actuating actuating) {
    entityManager.persist(actuating);
  }

  public List<Actuating> findActuatingAll() {
    final String query = "SELECT a FROM Actuating a";
    return entityManager.createQuery(query, Actuating.class).getResultList();
  }

  public List<Actuating> findActuatingByActuatorId(UUID actuatorId) {
    final String query = "SELECT a FROM Actuating a WHERE a.actuatorId = :actuatorId";
    return entityManager.createQuery(query, Actuating.class)
        .setParameter("actuatorId", actuatorId)
        .getResultList();
  }

  public List<Actuating> findActuatingByActuatorIdAndActuatorResult(UUID actuatorId, String actuatingResult) {
    final String query = "SELECT a FROM Actuating a WHERE a.actuatorId = :actuatorId AND a.actuatingResult = :actuatingResult";
    return entityManager.createQuery(query, Actuating.class)
        .setParameter("actuatorId", actuatorId)
        .setParameter("actuatingResult", actuatingResult)
        .getResultList();
  }

  public Actuating findActuatingByActuatingId(UUID actuatingId) {
    final String query = "SELECT a FROM Actuating a WHERE a.actuatingId=:actuatingId";
    return entityManager.createQuery(query, Actuating.class)
        .setParameter("actuatingId", actuatingId)
        .getResultList()
        .stream()
        .findFirst()
        .orElse(null);
  }
}
