FROM openjdk:17
LABEL authors="songmingyang"
WORKDIR /
ADD target/cr-review-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
EXPOSE 9094
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]