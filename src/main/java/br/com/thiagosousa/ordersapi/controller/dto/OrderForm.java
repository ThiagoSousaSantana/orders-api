package br.com.thiagosousa.ordersapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull @Valid
    private List<OrderItemForm> items = new ArrayList<>();

    @NotNull
    private Long customerId;

}
