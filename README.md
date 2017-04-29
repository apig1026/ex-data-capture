# ex-data-capture
An exercise project for data capturing using Spring-Boot.

## Installation

Mock-App and Mock-Server are two separate Spring-Boot applications 
using the same common foundation under the same project.
 
Both projects could be run by Gradle via 
```
bootRun
```

or to output as an executable jar file and run by following command: 
```
bootRepackage
```

```
java -jar {filename.jar} 
```

##### Docker:
**Run**
```
sudo docker run {port}:8080 ajoshow/mockapp
```
```
sudo docker run {port}:8081 ajoshow/mockserver 
```

**Build**  
*The Dockerfile are located at mock-server/docker and mock-app/docker,
you may need to gradle bootPackage first, and put executable jar file into the same 
directory where the Dockerfile is before build the image.*  


## API
Mock-App
```
[GET] http://{domain}:8080/ad-contents?do=fetch&v=1
[GET] http://{domain}:8080/ad-contents?do=adv-fetch&v=1
[POST] http://{domain}:8080/ad-contents/query?v=1
```
Mock-Server
```
[GET] http://{domain}:8081/ad-contents?v=1
```

## Description  
##### Mock-App

1. Fetch data from the given API in every 1 minute. The data is in a form of JSON.
2. Design a simple ORM Model and save the data to the H2 database.
3. Create a simple REST api to query the data from the database.
4. Redesign the implementation from 1), so it can fetch data from either the given API or the Mock server API.
5. Extend 4), fetch data from both source concurrently, but only the first response get to save to the database. The later responses will be ignored. 5 seconds timeout for both fetch tasks.  


##### Mock-Server

1. Create a API which returns data that has similar JSON data structure as the one from the given API.
2. Extend 1), the API has chance not return data, instead it blocks the process for 10 seconds before returns an empty JSON. 


##### Mock-Common

- Put shared objects and classes here. 
 
