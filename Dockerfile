FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8082
COPY target/moviesproject-*.jar /app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]