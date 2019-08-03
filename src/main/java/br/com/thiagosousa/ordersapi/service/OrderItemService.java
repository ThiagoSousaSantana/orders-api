package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.OrderItemForm;
import br.com.thiagosousa.ordersapi.model.Order;
import br.com.thiagosousa.ordersapi.model.OrderItem;
import br.com.thiagosousa.ordersapi.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    private OrderItemRepository repository;
    private ProductService productService;

    public OrderItemService(OrderItemRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    List<OrderItem> insertAll(List<OrderItemForm> formList, Order order){
        List<OrderItem> orderList = new ArrayList<>();

        formList.forEach(itemForm -> orderList.add(update(order, itemForm)));

        return repository.saveAll(orderList);
    }

    private OrderItem update(Order order, OrderItemForm itemForm) {
        var product = productService.findById(itemForm.getProductId());
        var totalItem = itemForm.getQuantity() * itemForm.getUnitPrice();

        return new OrderItem(product, itemForm.getQuantity(), itemForm.getUnitPrice(), totalItem, order);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    void deleteByOrderId(Long orderId) {
        repository.deleteAllByOrderId(orderId);
    }
}
