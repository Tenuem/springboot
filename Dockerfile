FROM openjdk:11-jdk

COPY gateway/target/gateway-1.0-SNAPSHOT.jar gateway.jar
COPY songs/target/songs-1.0-SNAPSHOT.jar songs.jar
COPY authors/target/authors-1.0-SNAPSHOT.jar authors.jar

COPY frontend /var/www/html
CMD java -jar songs.jar & sleep 12 && java -jar authors.jar & sleep 12 && java -jar gateway.jar

EXPOSE 8080