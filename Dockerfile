FROM maven:3.9.6-eclipse-temurin-21 as builder

LABEL org.opencontainers.image.title="Cobot"

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

# Runtime image
FROM eclipse-temurin:21-jdk

WORKDIR /app

RUN apt-get update && apt-get install -y bash && rm -rf /var/lib/apt/lists/*

COPY --from=builder /app/target /app
COPY docker/run /app/run

ENV COBOT_SECRET_TOKEN_FILE=/run/secrets/cobot_token

RUN chmod +x /app/run

CMD ["/app/run"]
