using MongoDB.Driver;

namespace dotnet_sample.Configuration;

public class MongoDBClient
{

    private IMongoClient client;

    public MongoDBClient()
    {
        this.client = new MongoClient("mongodb://localhost:27017");
    }

    public IMongoClient GetClient()
    {
        return this.client;
    }
    
    
    
}