
FROM eclipse-temurin:17.0.13_11-jdk

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# DOWNLOAD DEPENDENCIES
RUN ./mvnw dependency:go-offline

#COPY SOURCE CODE
COPY ./src /root/src
COPY ./data/db_books.sql /docker-entrypoint-initdb.d

# BUILD APP
RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java","-jar","/root/target/apirest-books-0.0.1-SNAPSHOT.jar"]