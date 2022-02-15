# Build Projects

FROM maven AS build

COPY ./Gateway/src /home/apps/GatewayApp/src
COPY ./Gateway/pom.xml /home/apps/GatewayApp

COPY ./Jobs/src /home/apps/JobsApp/src
COPY ./Jobs/pom.xml /home/apps/JobsApp

COPY ./Workers/src /home/apps/WorkersApp/src
COPY ./Workers/pom.xml /home/apps/WorkersApp

RUN mvn clean install -f /home/apps/GatewayApp/pom.xml
RUN mvn clean install -f /home/apps/JobsApp/pom.xml
RUN mvn clean install -f /home/apps/WorkersApp/pom.xml


# Deploy

FROM adoptopenjdk/openjdk11:alpine

COPY --from=build /home/apps/GatewayApp/target/Gateaway.jar /apps/
COPY --from=build /home/apps/JobsApp/target/Jobs.jar /apps/
COPY --from=build /home/apps/WorkersApp/target/Workers.jar /apps/
COPY start.sh  apps/

EXPOSE 8080
ENTRYPOINT ["/apps/start.sh"]