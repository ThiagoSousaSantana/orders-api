package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.repository.CustomerRepository;
import br.com.thiagosousa.ordersapi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Page<Customer> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Customer findBy(Long id){
        var customer = repository.findById(id);
        return customer.orElseThrow(() -> new ObjectNotFoundException("Customer not found with id: " + id));
    }

    public void delete(Long id){
        var customer = findBy(id);
        repository.delete(customer);
    }

    public Customer insert(Customer customer){
        customer.setId(null);
        return repository.save(customer);
    }


}
