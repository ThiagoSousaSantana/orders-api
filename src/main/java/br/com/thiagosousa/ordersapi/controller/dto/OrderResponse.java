package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String customerName;
    private Double total;
    private LocalDateTime registerDate;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.customerName = order.getCustomer().getName();
        this.total = order.getTotal();
        this.registerDate = order.getRegisterDateTime();
    }

}
