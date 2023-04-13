# Java Sample App

## Purpose

This app provides a working example of how to use Java and the MongoDB Sync Driver to connect to and work with data in your MongoDB database. 

## Demo Purposes Only

The code in this repository is not intended to be production ready. It is simply designed to provide a working that teams can borrow from to create their own systems. This code base has not been tuned for performance nor has it been checked for any security related issues. ***Do not put this code directly into production***


## Dependencies

The project has the following dependencies

* [Mongodb-driver-sync](https://www.mongodb.com/docs/drivers/java/sync/current/) version 4.9.0
* [Spring-boot](https://spring.io/) - Spring boot is used simply to eliminate the need to also package a app server with the code base. This project makes use of Spring for dependency injection only. It DOES NOT use any of the spring-data approaches.
* Java
* Maven

## Set Your Connection String
Set your connection string in the [application.properties](./src/main/resources/application.properties) file.

```
spring.data.mongodb.uri=<MongoDB Connection String>
```

## Running
To run the project type 

``` mvn spring-boot:run ``` from the command line.

You can also configure this command inside most popular IDE's as needed.


## Accessing

The project will expose a REST api located @ ```/customer ``` by which you can manipulate the customer data. The following endpoints are already existing

- ``` GET /customer/{id}``` - Pass in the HEX portion of the ObjectId to retrieve a given customer record by ID

- ``` GET /customer/?lastName=<last name>``` - Use a query paramter to get a list of customers matching the last name.

- ``` POST /customer ```- Create a new customer record and returns the resultig record

- ``` POSt /customer/search ``` - Uses the aggregation pipeline to do an $or search against first or last name. Takes a single parameter in the request body called ``` name ```
   - This could be a great example of where you can replace the $or with a $search.  Ultimately thats why this exists with an aggregation instead of a traditional fine 

## Usage Examples
```
curl --location 'localhost:8080/customer' \
--header 'Content-Type: application/json' \
--data '{
    "firstName": "Daffy",
    "lastName": "Duck",
    "title": "Entertainer",
    "address": {
        "street": "Magic Kingdom",
        "city": "Orlando",
        "state": "FL",
        "country": "USA"
    },
    "phones": [
        {
            "type": "main",
            "number": "(407) 939-5277",
            "countryPrefix": "1"
        }
    ]
}'
```

```
✗ curl --location --request GET 'localhost:8080/customer/6436d4fb683a624ebc12a444' \
--header 'Content-Type: application/json'

{"id":{"timestamp":1681315067,"date":"2023-04-12T15:57:47.000+00:00"},"firstName":"Daffy","lastName":"Duck","title":"Entertainer","address":{"city":"Orlando","state":"FL","country":"USA","street":"Magic Kingdom"},"phones":[{"type":"main","number":"(407) 939-5277","countryPrefix":"1"}]}
```

```
curl --location --request GET 'localhost:8080/customer/?lastName=Duck' \
--header 'Content-Type: application/json'
[{"id":{"timestamp":1681315067,"date":"2023-04-12T15:57:47.000+00:00"},"firstName":"Daffy","lastName":"Duck","title":"Entertainer","address":{"city":"Orlando","state":"FL","country":"USA","street":"Magic Kingdom"},"phones":[{"type":"main","number":"(407) 939-5277","countryPrefix":"1"}]}]
```


