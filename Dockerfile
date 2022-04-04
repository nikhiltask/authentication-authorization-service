FROM openjdk:17
ADD target/DockerAuthenticationService.jar DockerAuthenticationService.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","DockerAuthenticationService.jar"]