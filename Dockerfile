FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ADD target/classwork-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

#FROM adoptopenjdk/openjdk17:alpine-jre
#ADD target/springboot-postgres-docker-0.0.1-SNAPSHOT.jar app.jar

#ENTRYPOINT ["java","-jar", "app.jar"]