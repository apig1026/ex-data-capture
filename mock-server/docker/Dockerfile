# https://hub.docker.com/r/lwieske/java-8/
FROM lwieske/java-8:jre-8u121-slim

# Author
MAINTAINER ajoshow@gmail.com

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Make port 8081 available to the world outside this container
EXPOSE 8081

# Start the application with parameters
CMD java -jar /app/mock-server.jar