package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.model.Address;
import br.com.thiagosousa.ordersapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public void insertAll(List<Address> addresses){
        repository.saveAll(addresses);
    }

    public void delete(List<Address> addresses){
        repository.deleteAll(addresses);
    }

}
