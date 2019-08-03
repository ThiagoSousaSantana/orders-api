package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByCustomerNameContains(String customerName, Pageable pageable);
}
