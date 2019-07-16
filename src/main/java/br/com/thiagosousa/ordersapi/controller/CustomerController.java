package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.controller.dto.CustomerDto;
import br.com.thiagosousa.ordersapi.controller.dto.CustomerForm;
import br.com.thiagosousa.ordersapi.model.Customer;
import br.com.thiagosousa.ordersapi.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@Api(tags = { "customers" })
public class CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Insert a new customer")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<Customer> insert(@RequestBody @Valid CustomerForm form){
        var customer = service.insert(form);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @GetMapping
    @ApiOperation(value = "List all customers")
    public ResponseEntity<Page<CustomerDto>> listAll(Pageable pageable, String name, String email){
        if (name != null)
            return ResponseEntity.ok(service.findByName(pageable, name));

        if (email != null)
            return ResponseEntity.ok(service.findByEmail(pageable, email));

        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an customer by ID")
    public ResponseEntity<Customer> customerDetails(@PathVariable Long id){
        var customer = service.findBy(id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a customer")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id")
    @ApiOperation(value = "Update an existing customer")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<Customer> update(@RequestBody @Valid CustomerForm form, @RequestParam Long id){
        var customer = service.update(form, id);
        return ResponseEntity.ok(customer);
    }
}

