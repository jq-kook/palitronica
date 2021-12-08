FROM gradle:7.3.1-jdk-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle assemble || return 0
COPY . .
RUN gradle clean assemble
    
# actual container
FROM openjdk:17-slim
ENV ARTIFACT_NAME=payment-back-end.jar
ENV APP_HOME=/usr/app/
    
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/*.jar ${ARTIFACT_NAME}
    
EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
