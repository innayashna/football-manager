FROM openjdk:11.0.15-jre

WORKDIR /opt/app

COPY target/FootballManager-0.0.1-SNAPSHOT.jar football-manager.jar

ENTRYPOINT ["java", "-jar", "football-manager.jar"]