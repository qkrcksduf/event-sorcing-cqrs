val springBootVersion: String by project
val axonVersion = "4.4.3"
val springfox = "3.0.0"
val eureka = "2.2.6.RELEASE"
val gateway = "2.2.6.RELEASE"
val cloud = "2.3.1.RELEASE"

val implementation by configurations

dependencies {
  implementation("org.springframework.boot:spring-boot:${springBootVersion}")
  implementation("org.springframework.boot:spring-boot-autoconfigure:${springBootVersion}")
  implementation("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")
  implementation("org.springframework.boot:spring-boot-devtools:${springBootVersion}")
  implementation("org.axonframework:axon-spring-boot-starter:${axonVersion}")
  implementation("org.axonframework:axon-configuration:${axonVersion}")
  implementation("org.axonframework:axon-spring-boot-starter:${axonVersion}")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}")
  implementation ("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")

  implementation("net.jodah:typetools:0.6.2")
  implementation("io.springfox:springfox-core:${springfox}")
  implementation("io.springfox:springfox-swagger2:${springfox}")
  implementation("io.springfox:springfox-swagger-ui:${springfox}")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:${eureka}")
  implementation("org.springframework.cloud:spring-cloud-dependencies-parent:${cloud}")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:${gateway}")
  implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix:${gateway}")
  implementation("org.postgresql:postgresql")
  implementation("org.modelmapper:modelmapper:2.3.9")
  implementation("io.swagger:swagger-annotations:1.5.20")
  implementation("com.kenai.nbpwr:edu-umd-cs-findbugs-annotations:1.3.2-201002241900")

  runtimeOnly("org.postgresql:postgresql:42.2.18")
  compileOnly("org.projectlombok:lombok:1.18.16")
  annotationProcessor("org.projectlombok:lombok:1.18.16")
}
