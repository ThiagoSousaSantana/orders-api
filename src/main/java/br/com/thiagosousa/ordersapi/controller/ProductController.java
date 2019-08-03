package br.com.thiagosousa.ordersapi.controller;

import br.com.thiagosousa.ordersapi.controller.dto.ProductRequest;
import br.com.thiagosousa.ordersapi.model.Product;
import br.com.thiagosousa.ordersapi.service.ProductService;
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
@RequestMapping("/products")
@Api(tags = { "products" })
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Insert a new product")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<Product> insert(@RequestBody @Valid ProductRequest productRequest){
        var product = service.insert(productRequest);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    @ApiOperation(value = "List all products")
    public ResponseEntity<Page<Product>> listAll(Pageable pageable, String name){
        if (name != null) return ResponseEntity.ok(service.findAllBy(name,pageable));
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find an product by ID")
    public ResponseEntity<Product> productDetails(@PathVariable Long id){
        var customer = service.findById(id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a product")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/id")
    @ApiOperation(value = "Update an existing product")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Bad request")})
    public ResponseEntity<Product> update(@RequestBody @Valid ProductRequest form, @RequestParam Long id){
        var customer = service.update(form, id);
        return ResponseEntity.ok(customer);
    }
}

