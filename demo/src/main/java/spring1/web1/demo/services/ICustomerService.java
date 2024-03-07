package spring1.web1.demo.services;

import spring1.web1.demo.model.ClientFaultException;
import spring1.web1.demo.model.Customer;
import java.util.List;

public interface ICustomerService {

    Customer createCustomer(Customer customer) throws ClientFaultException;

    void updateCustomer(Customer customer, Integer id);

    void deleteCustomer(Integer id);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    List<Integer> getAllIds();
}
