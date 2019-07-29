FROM openjdk:8-jdk-alpine AS build
COPY src /usr/src/app/src
COPY .mvn /usr/src/app/.mvn
COPY pom.xml /usr/src/app
COPY mvnw /usr/src/app
WORKDIR /usr/src/app
RUN ./mvnw clean package

FROM openjdk:8-jre-alpine
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/wexprepag.api*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]