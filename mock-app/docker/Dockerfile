#https://hub.docker.com/r/frolvlad/alpine-oraclejdk8/
FROM frolvlad/alpine-oraclejdk8:slim

# Author
MAINTAINER ajoshow@gmail.com

# Spring Boot default working directory for tomcat.
VOLUME /tmp

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Make port available to the world outside this container
EXPOSE 8080

# Run the application with cammands
RUN sh -c 'touch /app/*'
ENV JAVA_OPTS="-Dapp.mockserver.datasource.url=http://127.0.0.1:8081/ad-contents?v=1"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app/mockapp.jar" ]