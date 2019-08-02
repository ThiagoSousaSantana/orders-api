package br.com.thiagosousa.ordersapi.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderItemForm {

    @NotNull
    private Long productId;
    @NotBlank @Min(value = 1)
    private Double quantity;

    @NotBlank @Min(value = 1)
    private Double unitPrice;

    public OrderItemForm() {
    }

    public OrderItemForm(@NotNull Long productId, @NotBlank Double quantity, @NotBlank Double unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
