package br.com.thiagosousa.ordersapi.controller.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull @Valid
    private List<OrderItemForm> items = new ArrayList<>();
    @NotNull
    private Long customerId;


    public List<OrderItemForm> getItems() {
        return items;
    }

    public void setItems(List<OrderItemForm> items) {
        this.items = items;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
