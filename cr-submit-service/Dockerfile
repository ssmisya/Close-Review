FROM openjdk:17
LABEL authors="songmingyang"
WORKDIR /
ADD target/cr-submit-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
EXPOSE 9093
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]