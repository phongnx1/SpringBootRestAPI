# Spring Boot CRUD Demo

## Prod Deployment - Standdlone Application
### Build
```
mvn clean package
```

### Run
java -jar target/spring-crud-demo.jar

### Navigate to your browser with
`http://domain:8080/api/v1/users/all`


## Docker

```
docker image build -t spring-boot-crud-demo:latest .
```