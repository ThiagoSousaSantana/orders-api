package br.com.thiagosousa.ordersapi.service;

import br.com.thiagosousa.ordersapi.controller.dto.ProductRequest;
import br.com.thiagosousa.ordersapi.model.Product;
import br.com.thiagosousa.ordersapi.repository.ProductRepository;
import br.com.thiagosousa.ordersapi.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService{

    @Autowired
    private ProductRepository repository;

    public Product insert(ProductRequest productRequest){
        return repository.save(productRequest.toProduct());
    }

    public Page<Product> findAllBy(String name, Pageable pageable){
        return repository.findAllByNameContains(pageable, name);
    }

    public Product findById(Long id){
        var product = repository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Product", "ID", id));
    }

    public Product update(ProductRequest productRequest, Long id){
        findById(id);
        var product = productRequest.toProduct();
        product.setId(id);
        return repository.save(product);
    }

    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
