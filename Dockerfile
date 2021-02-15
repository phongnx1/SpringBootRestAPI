# latest oracle openjdk is the basis
FROM openjdk:oracle

# copy jar file into container image under app directory
COPY target/spring-crud-demo.jar app/app.jar

# expose server port accept connections
EXPOSE 8080

# start application
CMD ["java", "-jar", "app/app.jar"]