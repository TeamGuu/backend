FROM amazoncorretto:17.0.6
MAINTAINER a8118199@gmail.com
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
