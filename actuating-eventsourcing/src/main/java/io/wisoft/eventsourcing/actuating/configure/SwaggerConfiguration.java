package io.wisoft.eventsourcing.actuating.configure;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.containsPattern;
import static com.google.common.base.Predicates.or;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private String version;
  private String title;
  private String description;

  @Bean
  public Docket api() {
    version = "v1";
    title = "Event Sourcing Actuating API Document";
    description = "WiSoft IoT Platform - Event Sourcing Actuating API Document";

    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(this.swaggerInfo(title, version, description));
  }

  private ApiInfo swaggerInfo(String title, String version, String description) {
    return new ApiInfoBuilder()
        .title(title)
        .description(description)
        .version(version)
        .build();
  }

}