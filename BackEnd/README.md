# code-challenge
## Software needed
In order to build and deploy this project you will need the following software:
* [Maven](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/products/docker-desktop)
* [Docker compose](https://docs.docker.com/compose/install/)

## How to run/test 
First of all clone the repository on your machine and go into de folder.
```shell script
git clone git@github.com:<username>/code-challenge.git
cd code-challenge
```

Build the application: 
```shell script
mvn clean pakcage
```
With that command we are able to generate the corresponding jar file contains the project.

Once we package the project, we can build and deploy the application:
```shell script
docker-compose up -d --build
```

Probably you will have to wait a couple of minutes before the Springboot application starts.

You can test if the application is running on [Swagger](http://localhost:8000/swagger-ui.html#/)

## Technologies and frameworks
- PostgreSQL for keeping the data in postgres table inside User_table table.
- SonarQube for code quality.
- Docker and docker-compose for integration and deploy.
- Flyway in order to update the database and have any migrations necesary up and running.
- Swagger is a tool that allows you to easily test your application endpoints, providing you a visual interface and documentation for each endpoint.

### Out of scope
* This data, from my point of view is sensible data and should be stored encrypted.

* Secure the application, like create users and roles to modify or extract data from the database.
