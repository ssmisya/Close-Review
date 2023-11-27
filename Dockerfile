FROM openjdk:17
LABEL authors="songmingyang"
WORKDIR /
ADD cr-user-service/target/cr-user-service-0.0.1-SNAPSHOT-exec.jar app1.jar
ADD cr-conference-service/target/cr-conference-service-0.0.1-SNAPSHOT.jar app2.jar
ADD cr-submit-service/target/cr-submit-service-0.0.1-SNAPSHOT.jar app3.jar
ADD cr-review-service/target/cr-review-service-0.0.1-SNAPSHOT.jar app4.jar
EXPOSE 8081 8082 8083 8084
EXPOSE 9091 9092 9093 9094
RUN java -jar app1.jar
RUN java -jar app2.jar
RUN java -jar app3.jar
RUN java -jar app4.jar
