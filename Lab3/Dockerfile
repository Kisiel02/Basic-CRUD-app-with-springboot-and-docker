FROM adoptopenjdk/openjdk11:alpine

COPY ./Gateway/target/Gateaway-0.0.1-SNAPSHOT.jar apps/
COPY ./Jobs/target/Jobs-0.0.1-SNAPSHOT.jar apps/
COPY ./Workers/target/Workers-0.0.1-SNAPSHOT.jar apps/
COPY start.sh  apps/


RUN chmod -R 777 /apps
ENTRYPOINT ["apps/start.sh"]