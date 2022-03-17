FROM openjdk:11
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY ./build/libs/*-all.jar p1-carlos.jar
EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -jar p1-carlos.jar