FROM openjdk:11

COPY target/gateway-1.0-SNAPSHOT.jar gateway.jar

ENTRYPOINT ["java", "-jar", "gateway.jar"]

EXPOSE 8080