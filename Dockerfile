FROM openjdk:11-jre-slim
LABEL authors="k9514"
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} education_project-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/education_project-1.0-SNAPSHOT.jar"]