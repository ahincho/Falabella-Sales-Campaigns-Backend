FROM amazoncorretto:21.0.5-alpine
WORKDIR /app
COPY target/campaigns-1.0.0.jar campaigns.jar
ENV SPRING_PROFILE=dev
ENV SPRINGDOC_PATH=/api/docs
ENV SPRINGDOC_SWAGGER_PATH=/api/swagger/
ENV SERVER_PORT=8080
EXPOSE 8080
LABEL name="Campaigns"
LABEL authors="Angel Hincho"
LABEL maintainer="ahincho"
CMD ["java", "-jar", "campaigns.jar", "--spring.profiles.active=${SPRING_PROFILE}"]