package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.CustomerDto;
import br.com.thiagosousa.ordersapi.controller.dto.CustomerForm;
import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.repository.CustomerRepository;
import br.com.thiagosousa.ordersapi.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
        return customer.orElseThrow(() -> new ObjectNotFoundException("Customer not found with id: " + id));
    }

    public void delete(Long id){
        var customer = findBy(id);
        addressService.delete(customer.getAddress());
        repository.delete(customer);
    }

    public Customer insert(CustomerForm form){
        var customer = repository.save(form.toCustomer());
        var address = form.getAddress().stream().map(addressForm ->
                addressForm.toAddress(customer)).collect(Collectors.toList());
        addressService.insertAll(address);
        return customer;
    }

}
