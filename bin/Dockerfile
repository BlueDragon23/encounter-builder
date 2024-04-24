FROM amazoncorretto:21-alpine3.19

RUN addgroup --system appuser && adduser --system --ingroup appuser appuser
USER appuser
VOLUME /home/appuser
ARG EXTRACTED=./build/extracted
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
COPY ./bin/entrypoint.sh ./
ENTRYPOINT ["/bin/sh", "./entrypoint.sh"]
