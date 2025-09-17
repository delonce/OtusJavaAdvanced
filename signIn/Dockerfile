FROM vegardit/graalvm-maven:latest-java21 AS builder

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn -Pnative native:compile

FROM mirekphd/jenkins-jdk17-on-ubuntu2204:latest

COPY --from=builder /app/target/signInApp /app/

CMD ["/app/signInApp"]