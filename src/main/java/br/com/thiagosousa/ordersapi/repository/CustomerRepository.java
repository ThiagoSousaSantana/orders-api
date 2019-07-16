package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Page<Customer> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Customer> findByEmail(String email);
}
