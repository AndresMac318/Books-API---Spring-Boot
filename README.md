# API Rest Books

This project was generated with Springboot 3.2.4 using the Java JDK v17.

Application built as a Library Management system, using Maven and Springboot framework in its version 3 in addition to adding technologies such as Spring Security, JUnit5, Mockito, Swagger, JWT and using Docker, Docker Compose to complement the system by adding security, software testing and automatic deployment in any environment, in addition to providing its respective documentation.

The concepts and technologies implemented were learned in the course [Spring Boot: Desarrollo de aplicaciones backend](https://www.udemy.com/course/spring-boot-2-desarrollo-de-aplicaciones-backend/?couponCode=24T2MT111824)

## Development server

### Prerequisite
You must have the following technologies on your host machine:

- Java jdk v17
- IDE de su preferencia
- MySQL, import database "db_books.sql" located in the folder /data/db_books.sql

### Environment variables
Next, configure the environment variables for the correct execution of the project, create a .env file for the configuration with docker. Also configure them in your IDE settings.

| Name     |   Value             |
|----------|---------------------|
| MYSQL_ROOT_PASSWORD | 654321   |
| MYSQL_USER | your_user         |
| MYSQL_PASSWORD | your_password |
| MYSQL_HOST | localhost         |
| MYSQL_PORT | 3306              |

### Run Project in Development Mode
Once you have set the environment variables, make sure that the project dependencies have been downloaded, then you can run the maven lifecycle methods in your IDE, you can do it also through the following commands through your OS terminal

Standing at the root of the project

- `./mvnw clean`
- `./mvnw package`
- `./mvnw install`

Ready, you can now run the project!

## Run server throught Docker Compose

Standing at the root of the project.

Run command `docker compose up`, ready the project was running in url [http://localhost:8082/v1/auth](http://localhost:8082/v1/auth)

## API Documentation with Swagger

Access the url [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)

Default user for test:

| Username |   Password    |
|----------|---------------|
| edita    | edita123      |


