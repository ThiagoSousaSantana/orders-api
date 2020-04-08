package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull @Min(value = 1)
    private Double price;

    public Product toProduct(){
        return new Product(null, name, description, price);
    }

}
