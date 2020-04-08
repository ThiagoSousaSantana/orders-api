package br.com.thiagosousa.ordersapi.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accessToken;
    private String tokenType = "Bearer";
    private UserResponse user;

    public JwtAuthenticationResponse(String accessToken, UserResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

}
