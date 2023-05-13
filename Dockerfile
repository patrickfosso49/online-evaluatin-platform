FROM openjdk:17-alpine
WORKDIR /app
COPY target/online-evaluation-platform.jar app.jar
ENTRYPOINT ["java" ,"-jar" , "app.jar"]
EXPOSE 8080