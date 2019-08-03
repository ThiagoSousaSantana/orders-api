package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.controller.dto.OrderForm;
import br.com.thiagosousa.ordersapi.controller.dto.OrderResponse;
import br.com.thiagosousa.ordersapi.model.Order;
import br.com.thiagosousa.ordersapi.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Api(tags = {"orders"})
@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> listAllBy(String customerName, Pageable pageable){
        if (customerName != null) return ResponseEntity.ok(service.findByCustomerName(customerName, pageable));
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PostMapping
    @ApiOperation(value = "Insert a new order")
    public ResponseEntity<Order> insert(@RequestBody @Valid OrderForm form){
        var order = service.insert(form);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(uri).body(order);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Order> update(@RequestBody OrderForm form, @PathVariable Long id){
        var order = service.update(form, id);
        return ResponseEntity.ok(order);
    }
}
