package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.OrderForm;
import br.com.thiagosousa.ordersapi.controller.dto.OrderItemForm;
import br.com.thiagosousa.ordersapi.model.Order;
import br.com.thiagosousa.ordersapi.repository.OrderRepository;
import br.com.thiagosousa.ordersapi.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final CustomerService customerService;
    private final OrderRepository repository;
    private final OrderItemService orderItemService;

    public OrderService(CustomerService customerService, OrderRepository repository, OrderItemService orderItemService) {
        this.customerService = customerService;
        this.repository = repository;
        this.orderItemService = orderItemService;
    }

    public Order insert(OrderForm orderForm){
        var customer = customerService.findBy(orderForm.getCustomerId());

        double total = 0;
        for (OrderItemForm item : orderForm.getItems())
            total += item.getQuantity() * item.getUnitPrice();

        var order = new Order(customer, LocalDateTime.now(), total);
        order = repository.save(order);

        var items = orderItemService.insertAll(orderForm.getItems(), order);

        order.setItems(items);
        return order;
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "ID", id));
    }
}
