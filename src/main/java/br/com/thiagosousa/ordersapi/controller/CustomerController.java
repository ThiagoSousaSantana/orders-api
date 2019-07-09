package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> listAll(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }
}
