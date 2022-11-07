FROM openjdk:11
MAINTAINER Aubrey Hlungwane (Hlungwaneak@protonmail.com)

COPY target/file-reader-service.jar file-reader-service.jar

ENTRYPOINT ["java","-jar","/file-reader-service.jar"]
EXPOSE 8080