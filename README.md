# Getting Started

This is a simple Spring Boot application with that reads representatives and companies from different system and create opportunities based on these information.

# Installation

You can install and run this application in multiple ways as shown below
The application landing page will display list of opportunities and you can also view the API documentation through swagger interface (http://localhost:{port}/swagger-ui.html/ which will display available endpoints and you can execute it from there as well. 

## Java Code

Just clone the repo into your local environment (check github for the How-To) after that you can run the below commands to have it up and running on port 8080.
>  mvn clean install
>  mvn spring-boot:run

## Docker

You can pull the docker image from docker hub locally and run it with the below command (Dockerfile can be found under docker folder).
> docker pull ahmedmoali/production-engineering-tools:v1

> docker run -p 8080:8080 ahmedmoali/production-engineering-tools

You can override the representative and companies endpoints if needed by passing the SPRING_APPLICATION_JSON as an environment variable 
> -e SPRING_APPLICATION_JSON='{"representatives":{"rest":{"endpoint":{"url":"http://www.mocky.io/v2/5df917f5300000d45688a1b4"}}},"companies":{"rest":{"endpoint":{"url":"http://www.mocky.io/v2/5df8fc57300000d45688a10e"}}}}'

## Kubernetes

You can deploy the application using kubernetes by just running the below commands (k8s files can be found under k8s folder).
> kubectl create -f service.yaml

> kubectl create -f deployment.yaml

After that you need to query the service and get the port that it is listening on by doing this 
> kubectl get svc

And replace the {port} place holder with the actual port http://localhost:{port}/  or  http://localhost:{port}/swagger-ui.html
