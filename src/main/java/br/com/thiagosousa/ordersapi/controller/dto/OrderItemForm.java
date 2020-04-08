package br.com.thiagosousa.ordersapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long productId;

    @Min(value = 1)
    private Double quantity;

    @Min(value = 1)
    private Double unitPrice;

}
