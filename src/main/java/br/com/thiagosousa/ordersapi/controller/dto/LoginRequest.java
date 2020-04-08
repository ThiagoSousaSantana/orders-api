package br.com.thiagosousa.ordersapi.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
