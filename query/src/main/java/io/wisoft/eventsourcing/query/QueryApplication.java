package io.wisoft.eventsourcing.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class QueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(QueryApplication.class, args);
  }
}
