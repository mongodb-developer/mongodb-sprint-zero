using dotnet_sample.Configuration;
using MongoDB.Driver;
using MongoDB.Bson;

namespace dotnet_sample.Services;

public class CustomerService
{

    private readonly IMongoCollection<Customer> _customerCollection;

    public CustomerService(MongoDBClient client)
    {
        var mongoDatabase = client.GetClient().GetDatabase("ms0");
        this._customerCollection = mongoDatabase.GetCollection<Customer>("customers");
    }

    
    

    public Customer GetById(string id)
    {
        var filter = Builders<Customer>.Filter.Eq("id", id);
        var customer = _customerCollection.Find(filter).First();
        return customer;
    }
    
    public List<Customer> GetAllCustomers()
    {
        var filter = Builders<Customer>.Filter.Empty;
        var customer = _customerCollection.Find(filter).ToList();
        return customer;
    }

    public Customer CreateCustomer(Customer customer)
    {
        _customerCollection.InsertOne(customer);
        return customer;
    }

    public List<Customer> CustomerSearch(String name)
    {
        List<Customer> results = _customerCollection.Aggregate()
            .Match(Builders<Customer>.Filter.Or(Builders<Customer>.Filter.Eq("lastName", name),
                Builders<Customer>.Filter.Eq("firstName", name))).ToList();
        return results;
    }


}