FROM anapsix/alpine-java:latest 
COPY target/library-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9003 
ENTRYPOINT java -jar ./app.jar
