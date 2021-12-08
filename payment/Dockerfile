FROM openjdk:17-slim
ARG JAR_FILE=../build/libs/*.jar
COPY ${JAR_FILE} payment-back-end.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/payment-back-end.jar"]