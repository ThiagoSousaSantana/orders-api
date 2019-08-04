FROM openjdk:12

ENV APP_ROOT /app

RUN mkdir ${APP_ROOT}

WORKDIR ${APP_ROOT}

COPY build/libs/*.jar ${APP_ROOT}/run.jar

ENTRYPOINT [ "java", "-jar", "run.jar" ]