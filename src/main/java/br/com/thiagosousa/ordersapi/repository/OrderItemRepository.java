package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}
