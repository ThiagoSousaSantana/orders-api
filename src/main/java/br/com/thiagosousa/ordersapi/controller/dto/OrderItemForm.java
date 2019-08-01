package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.OrderItem;
import br.com.thiagosousa.ordersapi.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderItemForm {

    @NotNull
    private Product product;
    @NotBlank
    private Double quantity;
    @NotBlank
    private Double unitPrice;

    public OrderItem toOrderItem(){
        return new OrderItem(product, quantity, unitPrice, quantity * unitPrice);
    }

    public OrderItemForm() {
    }

    public OrderItemForm(@NotNull Product product, @NotBlank Double quantity, @NotBlank Double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
