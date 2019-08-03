package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.CustomerDto;
import br.com.thiagosousa.ordersapi.controller.dto.CustomerForm;
import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.repository.CustomerRepository;
import br.com.thiagosousa.ordersapi.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final AddressService addressService;

    @Autowired
    public CustomerService(CustomerRepository repository, AddressService addressService) {
        this.repository = repository;
        this.addressService = addressService;
    }

    public Page<CustomerDto> findAll(Pageable pageable){
        return repository.findAll(pageable).map(CustomerDto::new);
    }

    public Customer findBy(Long id){
        var customer = repository.findById(id);
        return customer.orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", id));
    }

    public void delete(Long id){
        var customer = findBy(id);
        addressService.delete(customer.getAddress());
        repository.delete(customer);
    }

    public Customer insert(CustomerForm form){
        if (repository.findByEmail(form.getEmail()).isPresent())
            throw new IllegalArgumentException("Email already exists");

        var customer = repository.save(form.toCustomer());

        addressService.insert(form.getAddress());

        return customer;
    }

    public Customer update(CustomerForm form, Long id){
        var customer = findBy(id);
        updateFields(form, customer);

        addressService.insert(customer.getAddress());
        return repository.save(customer);
    }

    private void updateFields(CustomerForm form, Customer customer) {
        customer.setName(form.getName());
        customer.setEmail(form.getEmail());
        customer.setPhone(form.getPhone());
        customer.setAddress(form.getAddress());

    }

    public Page<CustomerDto> findByName(Pageable pageable, String name) {
        var customers = repository.findAllByNameContains(pageable, name);
        return customers.map(CustomerDto::new);
    }

    public Page<CustomerDto> findByEmail(Pageable pageable, String email) {
        var customers = repository.findAllByEmailContains(pageable, email);
        return customers.map(CustomerDto::new);
    }
}
