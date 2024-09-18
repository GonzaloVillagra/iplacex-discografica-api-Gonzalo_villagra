# STAGE 1
FROM gradle:jdk21 as builder

WORKDIR /app

COPY ./builder.gradle .
COPY ./setting.gradle .

COPY src ./src

RUN gradle build --no-daemon


#STAGE 2
FROM openjdk:21.jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar biblioteca-1.jar

EXPOSE 443

CMD [ "java", "-jar", "biblioteca-1.jar" ]