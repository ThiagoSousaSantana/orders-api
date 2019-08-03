package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.OrderForm;
import br.com.thiagosousa.ordersapi.controller.dto.OrderItemForm;
import br.com.thiagosousa.ordersapi.controller.dto.OrderResponse;
import br.com.thiagosousa.ordersapi.model.Order;
import br.com.thiagosousa.ordersapi.repository.OrderRepository;
import br.com.thiagosousa.ordersapi.service.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        double total = getTotalOrder(orderForm);

        var order = new Order(customer, LocalDateTime.now(), total);
        order = repository.save(order);

        var items = orderItemService.insertAll(orderForm.getItems(), order);

        order.setItems(items);
        return order;
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "ID", id));
    }

    public Page<OrderResponse> findByCustomerName(String customerName, Pageable pageable) {
        return repository.findByCustomerNameContains(customerName, pageable).map(OrderResponse::new);
    }

    public Page<OrderResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(OrderResponse::new);
    }

    public Order update(OrderForm form, Long id) {
        var order = findById(id);
        updateData(form, order);
        return repository.save(order);
    }

    private void updateData(OrderForm form, Order order) {
        var customer = customerService.findBy(form.getCustomerId());
        order.setCustomer(customer);

        order.setTotal(getTotalOrder(form));

        orderItemService.deleteByOrderId(order.getId());
        var items = orderItemService.insertAll(form.getItems(), order);
        order.setItems(items);
    }

    private double getTotalOrder(OrderForm orderForm) {
        double total = 0;
        for (OrderItemForm item : orderForm.getItems())
            total += item.getQuantity() * item.getUnitPrice();
        return total;
    }

    public void deleteById(Long id) {
        var order = findById(id);
        orderItemService.deleteByOrderId(id);
        repository.deleteById(id);
    }
}
