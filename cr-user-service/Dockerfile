FROM openjdk:17
LABEL authors="songmingyang"
WORKDIR /
ADD target/cr-user-service-0.0.1-SNAPSHOT-exec.jar app.jar
EXPOSE 8081
EXPOSE 9091
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]