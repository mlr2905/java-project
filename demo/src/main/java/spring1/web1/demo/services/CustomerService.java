package spring1.web1.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring1.web1.demo.model.ClientFaultException;
import spring1.web1.demo.model.Customer;
import spring1.web1.demo.model.CustomerStatus;
import spring1.web1.demo.model.ExceedVIPException;
import spring1.web1.demo.repository.CacheRepositoryImpl;
import spring1.web1.demo.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CacheRepositoryImpl cacheRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${maxvip}")
    private Integer maxVIP;

    @Value("${cache_on}")
    private Boolean cache_on;

    @Override
    public Customer createCustomer(Customer customer) throws ClientFaultException {
//        System.out.println(maxVIP);
        if (customer.getStatus() == CustomerStatus.VIP &&
                customerRepository.getCustomerByStatus(CustomerStatus.VIP).size() >= maxVIP)
        {
            throw new ExceedVIPException(String.format("Cannot create more VIP customers, for customer: %s %s.", customer.getLastName(), customer.getFirstName()));
        }
        // without id
        //return customerRepository.createCustomer(customer);

        // with id
        return customerRepository.createCustomerReturnId(customer);
    }

    @Override
    public void updateCustomer(Customer customer, Integer id) {
        customerRepository.updateCustomer(customer, id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        try {

            if (cache_on && cacheRepository.isKeyExist(String.valueOf(id))) {
                String customer = cacheRepository.getCacheEntity(String.valueOf(id));
                System.out.println("reading from cache " + customer);
                return objectMapper.readValue(customer, Customer.class);
            }

            Customer result = customerRepository.getCustomerById(id);

            if (cache_on) {
                cacheRepository.createCacheEntity(String.valueOf(id), objectMapper.writeValueAsString(result));
            }
            return result;

        } catch (JsonProcessingException e) {
            System.out.println(e);
            throw new IllegalStateException("cannot write json of customer");
        }
    }

    @Override
    public List<Integer> getAllIds() {
        return customerRepository.getAllIds();
    }


}
