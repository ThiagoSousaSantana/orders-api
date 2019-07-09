package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Page<Customer> findAll(Pageable pageable);
}
