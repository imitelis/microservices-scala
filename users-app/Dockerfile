FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/scala-2.13/users-app-assembly-1.0.jar /app/app.jar

EXPOSE 8080
EXPOSE 8081
EXPOSE 8082

CMD ["java", "-jar", "app.jar"]
