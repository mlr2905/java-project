package spring1.web1.demo.repository;
import spring1.web1.demo.model.ClientFaultException;
import spring1.web1.demo.model.Customer;
import spring1.web1.demo.model.CustomerStatus;
import spring1.web1.demo.model.NamedAlreadyExistException;

import java.util.List;

public interface ICustomerRepository {

    String createCustomer(Customer customer);

    Customer createCustomerReturnId(Customer customer) throws NamedAlreadyExistException, ClientFaultException;

    void updateCustomer(Customer customer, Integer id);

    void deleteCustomer(Integer id);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    List<Integer> getAllIds();

    List<Customer> getCustomerByStatus(CustomerStatus status);
}
