package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Customer> findByEmail(String email);

    Page<Customer> findAllByNameContains(Pageable pageable, String name);

    Page<Customer> findAllByEmailContains(Pageable pageable, String email);
}
