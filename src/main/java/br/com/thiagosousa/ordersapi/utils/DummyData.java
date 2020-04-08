package br.com.thiagosousa.ordersapi.utils;

import br.com.thiagosousa.ordersapi.model.Address;
import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.model.Product;
import br.com.thiagosousa.ordersapi.model.User;
import br.com.thiagosousa.ordersapi.repository.AddressRepository;
import br.com.thiagosousa.ordersapi.repository.CustomerRepository;
import br.com.thiagosousa.ordersapi.repository.ProductRepository;
import br.com.thiagosousa.ordersapi.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DummyData {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    public DummyData(CustomerRepository customerRepository, UserRepository userRepository,
                     ProductRepository productRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
    }


    @PostConstruct
    public void inserts(){
        var aragorn = new Customer(null, "Aragorn", "aragorn@email.com", "+559999999", null);
        var legolas = new Customer(null, "Legolas", "legolas@email.com", "+559999999", null);
        var gimli = new Customer(null, "Gimli", "gimli@email.com", "+559999999", null);
        var gandalf = new Customer(null, "Gandalf", "boromir@email.com", "+559999999", null);
        var frodo = new Customer(null, "Frodo", "frodo@email.com", "+559999999", null);
        var bilbo = new Customer(null, "Bilbo", "bilbo@email.com", "+559999999", null);
        customerRepository.saveAll(Arrays.asList(aragorn, legolas, gandalf, gimli, frodo, bilbo));

        var bilboAddress = new Address(null, "Bag End", "Bag End way", "Hobbiton", "00000-000", "Middle earth");
        addressRepository.save(bilboAddress);
        bilbo.setAddress(bilboAddress);

        var user = new User(null,
                "Administrator",
                "admin",
                "admin@mail.com",
                "$2a$10$rGwBxCDKwZkScoqLC3tsHOqruMPcv3HHjkFjlmgvmAUvyD.IN.LMi",
                new HashSet<>());
        userRepository.save(user);

        var product1 = new Product(null, "Product 1", "First product", 10.19);
        var product2 = new Product(null, "Product 2", "Second product", 10.99);
        var product3 = new Product(null, "Product 3", "Third product", 10.00);
        var product4 = new Product(null, "Product 4", "fourth product", 10.56);
        productRepository.saveAll(Arrays.asList(product1,product2,product3,product4));
    }
}
