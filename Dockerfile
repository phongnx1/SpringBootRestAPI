FROM openjdk:11-jdk
ARG JAR_FILE=target/spring-crud-demo.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


