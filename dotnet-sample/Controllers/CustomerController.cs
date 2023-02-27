using dotnet_sample.Services;
using Microsoft.AspNetCore.Mvc;

namespace dotnet_sample.Controllers;

[ApiController]
[Route("customer")]
public class CustomerController : ControllerBase
{
    private readonly ILogger<CustomerController> _logger;
    private CustomerService service;

    public CustomerController(ILogger<CustomerController> logger, CustomerService service)
    {
        _logger = logger;
        this.service = service;
    }
    
    [HttpGet]
    public List<Customer> Get()
    {
        return this.service.GetAllCustomers();
    }
    

    [HttpGet("{id}")]
    public Customer Get(string id)
    {
        return this.service.GetById(id);
    }

    [HttpPost]
    public Customer Create([FromBody] Customer customer)
    {
        return this.service.CreateCustomer(customer);
    }
    
    [HttpPost("search")]
    public List<Customer> CustomerSearch([FromBody] Dictionary<String, String> request)
    {
        String name = request.GetValueOrDefault("name");
        Console.WriteLine(name);

        return this.service.CustomerSearch(name);
    }
    
    
}
