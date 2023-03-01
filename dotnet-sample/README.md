# .Net Core Sample App

## Purpose

This app provides a working example of how to use .Net Core and MongoDB 

## Demo Purposes Only

The code in this repository is not intended to be production ready. It is simply designed to provide a working that teams can borrow from to create their own systems. This code base has not been tuned for performance nor has it been checked for any security related issues. ***Do not put this code directly into production***


## Dependencies

The project has the following dependencies

* [MongoDB C# Driver](https://www.mongodb.com/docs/drivers/csharp/current/) version 2.19
* [.Net Core 6](https://dotnet.microsoft.com/en-us/download/dotnet/6.0) version 6 
* [.Net CLI](https://learn.microsoft.com/en-us/dotnet/core/tools/)



## Running
To run the project type 

``` dotnet run ``` from the command line.

You can also configure this command inside most popular IDE's as needed.


## Accessing

The project will expose a REST api located @ ``` /customer ``` by which you can manipulate the customer data. The following endpoints are already existing

- ``` GET /customer/{id}``` - Pass in the HEX portion of the ObjectId to retrieve a given customer record by ID
- ``` POST /customer ```- Create a new customer record and returns the resultig record
- ``` POSt /customer/search ``` - Uses the aggregation pipeline to do an $or search against first or last name. Takes a single parameter in the request body called ``` name ```
   - This could be a great example of where you can replace the $or with a $search.  Ultimately thats why this exists with an aggregation instead of a traditional fine 


