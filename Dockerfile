FROM adoptopenjdk/openjdk13:ubi
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]