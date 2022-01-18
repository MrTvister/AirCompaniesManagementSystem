FROM maven:3.8.4-jdk-11-slim
ADD target/AirCompaniesManagementSystem-0.0.1-SNAPSHOT.jar AirCompaniesManagementSystem-0.0.1-SNAPSHOT.jar
#ADD . .
EXPOSE 8080
ENTRYPOINT ["java","-jar","/AirCompaniesManagementSystem-0.0.1-SNAPSHOT.jar"]