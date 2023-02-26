FROM maven:3.8.6-openjdk-18 as builder

COPY --chown=maven:maven .  /home/maven/src
WORKDIR /home/maven/src
RUN mvn clean package

FROM eclipse-temurin:18-jdk-jammy
EXPOSE 8080
COPY --from=builder /home/maven/src/target/zerend-0.1.jar /app/
WORKDIR /app
ENTRYPOINT java -server -jar zerend-0.1.jar
