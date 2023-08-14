FROM openjdk:17

EXPOSE 8080

ARG JAR_FILE=/build/libs/preonboard-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar"]
