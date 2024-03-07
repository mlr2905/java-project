package spring1.web1.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring1.web1.demo.model.ClientFaultException;
import spring1.web1.demo.model.Customer;
import spring1.web1.demo.model.TVMazeShowResponse;
import spring1.web1.demo.repository.CustomerRepository;
import spring1.web1.demo.services.CustomerService;
import spring1.web1.demo.services.UserServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserServiceClient apiClient;

    @GetMapping
    public List<Customer> get()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity getById(@PathVariable Integer id)
    {
        Customer result = customerService.getCustomerById(id);
        if (result != null ) {
            return new ResponseEntity<Customer>(result, HttpStatus.OK);
        }
        return new ResponseEntity<String>("{ \"Warning\": \"not found customer with Id " + id + "\" }",
                HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Customer customer) throws JsonProcessingException, ClientFaultException {
        try {
            String json = objectMapper.writeValueAsString(customer);
            System.out.println(json);
            Customer c = objectMapper.readValue(json, Customer.class);
            //throw new Ex1
            System.out.println(c);
            Customer resultCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<Customer>(resultCustomer, HttpStatus.CREATED);
        }
        catch (ClientFaultException e) {
            return new ResponseEntity<String>(String.format("{ Error: '%s' }", e.toString()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(String.format("{ Error: '%s' }", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/{id}")
    public Customer put(@PathVariable Integer id, @RequestBody Customer customer) {
        customerService.updateCustomer(customer, id);
        return customer;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id)
    {
        customerService.deleteCustomer(id);
    }

    @GetMapping(value = "/ids")
    public List<Integer> getIds()
    {
        return customerService.getAllIds();
    }

    @GetMapping(value = "/shows/{id}")
    public TVMazeShowResponse getIds(@PathVariable Integer id)
    {
        return apiClient.getShow(id);
    }


}
