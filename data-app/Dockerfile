FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/scala-2.12/data-app-assembly-1.0.jar /app/app.jar

EXPOSE 9000

CMD ["java", "-jar", "app.jar"]
