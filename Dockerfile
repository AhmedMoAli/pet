FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sf_tooling_pet.jar
ENTRYPOINT ["java","-jar","/sf_tooling_pet.jar"]