# STAGE 1
FROM gradle:jdk21 as builder

WORKDIR /app

COPY ./build.gradle . 
COPY ./settings.gradle .

COPY src ./src

RUN gradle build --no-daemon
RUN ls /app/build/libs

# STAGE 2
FROM openjdk:21-jdk-slim 

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar discografia.jar

EXPOSE 8080

CMD [ "java", "-jar", "discografia.jar" ]
