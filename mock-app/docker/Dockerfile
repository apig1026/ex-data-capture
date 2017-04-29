# https://hub.docker.com/r/lwieske/java-8/
FROM lwieske/java-8:jre-8u121-slim

# Author
MAINTAINER ajoshow@gmail.com

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Start the application with parameters
CMD java -Dapp.mockserver.datasource.url=http://127.0.0.1:8081/ad-contents?v=1 -jar /app/mock-app.jar