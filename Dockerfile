# STAGE 1
FROM gradle:jdk21 as builder

WORKDIR /app

COPY ./build.gradle . 
COPY ./settings.gradle .

COPY src ./src

RUN gradle build --no-daemon

<<<<<<< Updated upstream

#STAGE 2
FROM openjdk:21-slim
=======
# STAGE 2
FROM openjdk:21-jdk-slim 
>>>>>>> Stashed changes

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar biblioteca-1.jar

EXPOSE 443

CMD [ "java", "-jar", "biblioteca-1.jar" ]
