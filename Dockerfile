FROM azul/zulu-openjdk:8
MAINTAINER Pieter Cachet <pcvdw89@gmail.co.za>
ARG JAR_FILE
COPY ./target/${JAR_FILE} /app/app.jar
RUN apt-get update -y && apt-get install curl -y
HEALTHCHECK --start-period=120s CMD curl --fail http://localhost:8080/health || exit 1
EXPOSE 8080
RUN useradd -r myapp
USER myapp
ENTRYPOINT ["java","-server","-Djava.security.egd=file:/dev/./urandom","-jar", "/app/app.jar"]
