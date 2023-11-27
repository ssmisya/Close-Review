FROM openjdk:17
LABEL authors="songmingyang"
WORKDIR /
ADD target/cr-conference-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
EXPOSE 9092
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]