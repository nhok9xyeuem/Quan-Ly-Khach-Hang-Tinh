package manager.service.impl;

import manager.model.Customer;
import manager.repository.CustomerRepository;
import manager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
    customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
    customerRepository.delete(id);
    }
}
