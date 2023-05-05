# MongoDB Sprint Zero (MS0) Code Assets for Spring Boot (Maven)

A template modeled after [ms0.py](../ms0.py) for Java [Spring Boot](https://spring.io/) and [Spring Data](https://spring.io/projects/spring-data). The design of this application is loosely based on the [Spring Boot Integration with MongoDB Tutorial](https://www.mongodb.com/compatibility/spring-boot).

[Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb) is a main module of Spring Data. This application showcases two popular classes: [MongoRepository](https://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/repository/MongoRepository.html) and [MongoTemplate](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo-template). MongoRepository provides a higher-level, type-safe API for performing basic CRUD operations on a MongoDB collection while MongoTemplate provides a low-level, more flexible way to perform MongoDB operations, such as aggreations.  

Other helpful resources:
* [Spring Data MongoDB - Reference Documentation](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/) 
* [Introduction to Spring Data MongoDB](https://www.baeldung.com/spring-data-mongodb-tutorial)

## Getting Started
### Requirements
This application is dependant on Java 17. On MacOS this can be installed using Brew:
```zsh
brew install openjdk@17
```
You'll also need to update your `JAVA_HOME` and `PATH` in `.zprofile` as follows:
```zsh
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@17/17.0.6
PATH=$JAVA_HOME/bin:$PATH
export PATH
```
### Set Your Connection String
Set your connection string in the [application.properties](./ms0/src/main/resources/application.properties) file.

### Configuring the Database and Collection
The database is set in [application.properties](./ms0/src/main/resources/application.properties). By default it's set to `admin`, which is required for teh `currentOp` command to run. 

The collection is set via the `@Document("sandbox")` annotation in [Thing.java](./ms0/src/main/java/com/example/ms0/model/Thing.java).

### Package The Application
```zsh
ms0✗ mvn package
```
## Running The Application
```zsh
ms0✗ java -jar target/ms0-0.0.1-SNAPSHOT.jar <arg>
```

The following arguments are supported:
* insert 
* findIt 
* updateMatched 
* replaceMatched
* upsert 
* deleteAll
* currentOp

For example:
```zsh
✗ java -jar target/ms0-0.0.1-SNAPSHOT.jar insert

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.0.2)

Inserting a Thing...

✗ 
```

# Reviewing the Source
Check out [Ms0Application.java](https://github.com/wbleonard/mongodb-sprint-zero/blob/SpringBoot/java-spring-boot-maven/ms0/src/main/java/com/example/ms0/Ms0Application.java)

All criticism is welcome :-).


