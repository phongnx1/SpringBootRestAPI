# Spring Boot CRUD Demo

## Prod Deployment - Standdlone Application
### Build
```
mvn clean package
```

### Run
java -Dproperty.name="application-prod.properties" -jar target/spring-crud-demo.jar

### Navigate to your browser with
`http://domain:8080/api/v1/users/all`