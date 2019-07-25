package br.com.thiagosousa.ordersapi;

import br.com.thiagosousa.ordersapi.model.Address;
import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.model.Product;
import br.com.thiagosousa.ordersapi.model.User;
import br.com.thiagosousa.ordersapi.repository.AddressRepository;
import br.com.thiagosousa.ordersapi.repository.CustomerRepository;
import br.com.thiagosousa.ordersapi.repository.ProductRepository;
import br.com.thiagosousa.ordersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.TimeZone;

@EnableSwagger2
@EnableSpringDataWebSupport
@SpringBootApplication
@EntityScan(basePackageClasses = {
		OrdersApiApplication.class,
		Jsr310JpaConverters.class
})
public class OrdersApiApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(OrdersApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) {

		var aragorn = new Customer("Aragorn", "aragorn@email.com", "+559999999");
		var legolas = new Customer("Legolas", "legolas@email.com", "+559999999");
		var gimli = new Customer("Gimli", "gimli@email.com", "+559999999");
		var gandalf = new Customer("Gandalf", "boromir@email.com", "+559999999");
		var frodo = new Customer("Frodo", "frodo@email.com", "+559999999");
		var bilbo = new Customer("Bilbo", "bilbo@email.com", "+559999999");
		customerRepository.saveAll(Arrays.asList(aragorn,legolas,gandalf,gimli,frodo,bilbo));

		var bilboAddress = new Address("Bag End", "Bag End way", "Hobbiton", "00000-000", "Middle earth");
		bilboAddress.setCustomer(bilbo);
		addressRepository.save(bilboAddress);

		var user = new User("Administrator", "admin","admin@mail.com","$2a$10$rGwBxCDKwZkScoqLC3tsHOqruMPcv3HHjkFjlmgvmAUvyD.IN.LMi");
		userRepository.save(user);

		var product1 = new Product(null, "Product 1", "First product", 10.19);
		var product2 = new Product(null, "Product 2", "Second product", 10.99);
		var product3 = new Product(null, "Product 3", "Third product", 10.00);
		var product4 = new Product(null, "Product 4", "fourth product", 10.56);
		productRepository.saveAll(Arrays.asList(product1,product2,product3,product4));

	}
}
