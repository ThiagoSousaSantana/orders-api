package br.com.thiagosousa.ordersapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ApiResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success;
    private String message;

}
