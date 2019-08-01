package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.model.Order;
import br.com.thiagosousa.ordersapi.repository.OrderRepository;
import br.com.thiagosousa.ordersapi.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository repository, OrderItemService orderItemService) {
        this.repository = repository;
        this.orderItemService = orderItemService;
    }


    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "ID", id));
    }
}
