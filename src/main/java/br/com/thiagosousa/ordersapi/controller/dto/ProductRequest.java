package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ProductRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;
    private String description;
    @NotNull @Min(value = 1)
    private Double price;

    public Product toProduct(){
        return new Product(null, name, description, price);
    }

    public ProductRequest(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
