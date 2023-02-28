# MS0
Set of code assets to accelerat the MongoDB Sprint Zero process. 

## Demo Purposes Only
The code contained in this repository is intended simply to help boostrap the MongoDB Spring Zero process. The code works. but is **NOT PRODUCTION READY** and is not  intended to be.  

MongoDB Sprint Zero is about helping your customers
 - Quickly start consuming credits
 - Be able to maintain momentum in their development and buying process
 - Help them have something to demonstrate the value at the end of the Sprint


## How to Use

1. Clone the Repo down to your local machine
2. Identify which sample project is the best fit for your client
3. Make any necessary pro-active changes. This includes potentially
   - Adding in customer business objects based on your clients needs
   - CSFLE configuration if that's critical for your customer
   - Creating new sample data records



## Organization

At the top level you have shared assets, including

- [``` sample-data ```](sample_data/README.md) folder that contains some mgenerate compliant JSON scripts for creating fake customers
- [``` java-sample ```](java-sample/README.md) - Java project that uses the native MongoDB Java Driver to connect
- [``` spring-data ```](spring-data/README.md) - Java project using the Spring-boot and Spring-data packages to access the DB
- [``` dotnet-sample ```](dotnet-sample/README.md) - C# and .Net CORE webapi that connects to the DB using the native MongoDB driver



All of the project use essentially the same concept. 

### Project Structure

#### Controller
Some level of Controller class that exposes a REST API for @ ``` /customer ``` for the teams to access via a tool like Postman. This isn't directly related to the DB, but does make the entire project feel more real and will simplify their work to create a demonstrable prototype. 

#### Service
A package for service classes that can contain any business logic that the cusotmer would perform. This is merely there to try to show some best practices for how to create a project. 

#### DAO / Repository
This layer can have multiple names, but represents the data access layer of the project. .Net and Spring-Data will tend to use the repository pattern. 


### Content

Each project use the same example for a Customer object. This allows you to take the same sample-data and reuse it in any concept. 

The intent is to create a simple object that can start to demonstrate the benefits of the document model. If your customer gave you specific business objects that they want to use, then replace the default Customer object with the ones provided by your customer. Their objects should always take precedence. 

The default is simply there to give you a fully connected starting place with working examples of a query, an insert, and an aggregation query. 


