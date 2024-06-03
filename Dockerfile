FROM maven:3.9.5-sapmachine-21 AS build
WORKDIR /opt/rozproszenie2

COPY ./ /opt/rozproszenie2
RUN mvn clean install -DskipTests
FROM openjdk:21-rc-oracle

COPY --from=build /opt/rozproszenie2/target/*.jar rozproszenie2.jar

ENV PORT 8081
EXPOSE $PORT

ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","rozproszenie2.jar"]