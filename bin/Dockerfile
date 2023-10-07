FROM eclipse-temurin:17-jdk

RUN addgroup --system appuser && adduser --system --ingroup appuser appuser
USER appuser
VOLUME /home/appuser
ARG EXTRACTED=./build/extracted
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]