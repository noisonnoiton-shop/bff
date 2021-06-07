FROM openjdk:8-jdk-oracle
ADD target/awesome-bff-service-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-jar","/app.jar"]