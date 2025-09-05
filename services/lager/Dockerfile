# Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -U -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# Runtime
FROM eclipse-temurin:21-jre
ENV TZ=UTC JAVA_TOOL_OPTIONS="-XX:+UseZGC -XX:+ZGenerational"
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/app/app.jar"]
