package br.com.thiagosousa.ordersapi.repository;

import br.com.thiagosousa.ordersapi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);

    @Transactional
    void deleteAllByOrderId(Long orderId);
}
