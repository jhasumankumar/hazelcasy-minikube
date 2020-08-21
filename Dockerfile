FROM openjdk:11
COPY target/*.jar /opt/party-booking.jar
WORKDIR /opt
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "party-booking.jar"]