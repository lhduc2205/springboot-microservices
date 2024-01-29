FROM openjdk:17-jdk-alpine AS builder
WORKDIR extracted
ADD target/*.jar api-gateway-app.jar
RUN java -Djarmode=layertools -jar api-gateway-app.jar extract

FROM openjdk:17-jdk-alpine
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./
EXPOSE 8000
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]