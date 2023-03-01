using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace dotnet_sample;

[BsonIgnoreExtraElements]
public class Customer
{
    [BsonId]
    [BsonRepresentation(BsonType.ObjectId)]
    public string? id { get; set; }
    public string lastName { get; set; }
    public string firstName { get; set; }
    public string title { get; set; }
    public Address address { get; set; }
    public List<Phone> phones { get; set; }
    
    
}