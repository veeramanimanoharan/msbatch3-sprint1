FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sprintapp.jar
ENTRYPOINT ["java","-jar","/sprintapp.jar"]
