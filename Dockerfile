FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y apache2
RUN apt-get install -y default-jre

COPY gateway/target/gateway-1.0-SNAPSHOT.jar gateway.jar
COPY songs/target/songs-1.0-SNAPSHOT.jar songs.jar
COPY authors/target/authors-1.0-SNAPSHOT.jar authors.jar

COPY frontend /var/www/html
#CMD java -jar gateway.jar && java -jar authors.jar && java -jar songs.jar

EXPOSE 8080
EXPOSE 9090