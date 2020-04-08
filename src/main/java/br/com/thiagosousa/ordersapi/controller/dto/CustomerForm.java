package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Address;
import br.com.thiagosousa.ordersapi.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private String phone;

    @NotNull
    private Address address;

    public Customer toCustomer(){
        return new Customer(null, this.name, this.email, this.phone, this.address);
    }

}
