FROM openjdk:11-jdk

COPY target/songs-1.0-SNAPSHOT.jar songs.jar

CMD java -jar songs.jar
RUN mkdir files

EXPOSE 8080