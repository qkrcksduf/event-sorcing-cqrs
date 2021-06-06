package io.wisoft.eventsourcing.actuating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ActuatingEventSourcingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ActuatingEventSourcingApplication.class, args);
  }

}
