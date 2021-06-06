FROM openjdk:11-jdk
MAINTAINER cheolho@edu.hanbat.ac.kr

WORKDIR /
ADD ./build/libs/query-*.jar app.jar

EXPOSE 9003

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]