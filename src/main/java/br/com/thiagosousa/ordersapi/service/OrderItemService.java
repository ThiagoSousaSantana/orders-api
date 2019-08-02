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

    public List<OrderItem> insertAll(List<OrderItemForm> formList, Order order){
        List<OrderItem> orderList = new ArrayList<>();

        for (OrderItemForm itemForm : formList) {
            var product = productService.findById(itemForm.getProductId());

            var totalItem = itemForm.getQuantity() * itemForm.getUnitPrice();

            var orderItem = new OrderItem(product, itemForm.getQuantity(), itemForm.getUnitPrice(), totalItem, order);
            orderList.add(orderItem);
        }

        return repository.saveAll(orderList);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
