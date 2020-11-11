# Team Messaging Extensions coding challenge

This is a Spring Boot project with Maven as build tool. The goal of this challenge is to determine how you build a service based on JVM technologies.
This means, apart from Spring Boot you are open to use any technology you want. If this would be production code, how would you test it? The service within this repository does not work like it should.

## Import and compile
Take a look at the project and install all the dependencies and import it. Compile the project with Maven. Please fix it, if this does not work. 

## Run 

Run the project. If the application doesn't come up correctly, please fix it.The application has a dependency to MongoDB. Make sure that a MongoDB instance is running. 

There are two Endpoints available, which you can query for log entries and metadata: 

 - `http://localhost:8080/logs`
 - `http://localhost:8080/logs/count`

## Extend - Create 

An endpoint for adding logs is missing. Please make it available under `http://localhost:8080/logs/` through HTTP POST. The incoming logs should be stored in the database. You could use Postman to build and fire an HTTP POST Request. 

## Extend - Get By ID

Add another Endpoint which is available under `http://localhost:8080/logs/{id}`. 
The Endpoint should return the log  with the given id from the database. 

## Extends - Update

Build another Endpoint which allows the manipulation of an existing log. 

## Extend - Scheduler

There is a class `ScheduledTask` with the method `runBackgroundJob()`. 
Configure the application that way, that the method is executed every 5 seconds in the background. 

## Build and run  the Docker image

Build a Docker image with the application in it and call it `liveperson/me:0.0.1`. Try to run the Docker container that way that the application is available under `http://localhost:8080` on the Host system.

## Extend - Advanced

Do you think MongoDB is a good database for this kind of data? Maybe you can replace MongoDB with Kafka? Let's give it a try.