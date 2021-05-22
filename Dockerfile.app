FROM gradle:6.8.3-jdk15 as build
LABEL stage=builder

RUN mkdir -p /app /gradle-cache
WORKDIR /app
COPY build.gradle settings.gradle ./
RUN gradle -i  --no-daemon --gradle-user-home=/gradle-cache downloadDependencies

COPY src ./src
RUN gradle -i --gradle-user-home=/gradle-cache --no-daemon build -x test

FROM openjdk:15-jdk-alpine
COPY --from=build /app/build/libs/telegram-bot-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]