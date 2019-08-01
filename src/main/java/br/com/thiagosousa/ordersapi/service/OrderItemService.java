package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.OrderItemForm;
import br.com.thiagosousa.ordersapi.model.OrderItem;
import br.com.thiagosousa.ordersapi.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private OrderService orderService;
    private OrderItemRepository repository;

    @Autowired
    public OrderItemService(OrderService orderService, OrderItemRepository repository) {
        this.orderService = orderService;
        this.repository = repository;
    }

    public List<OrderItem> insertAll(List<OrderItemForm> formList, Long orderId){
        var order = orderService.findById(orderId);
        var orderList = formList.stream().map(OrderItemForm::toOrderItem).collect(Collectors.toList());
        orderList.forEach((item) -> item.setOrder(order));
        return repository.saveAll(orderList);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
