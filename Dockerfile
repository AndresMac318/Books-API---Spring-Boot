
FROM eclipse-temurin:17.0.13_11-jdk AS dependencies

WORKDIR /app
COPY ./pom.xml ./mvnw /app/
COPY ./.mvn /app/.mvn
RUN ./mvnw dependency:go-offline

FROM dependencies AS build

WORKDIR /app
COPY ./src /app/src
COPY ./data/db_books.sql /docker-entrypoint-initdb.d
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:17.0.13_11-jdk AS production

WORKDIR /app
COPY --from=build /app/target/*.jar /app/target/apirest-books-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/target/apirest-books-0.0.1-SNAPSHOT.jar"]
