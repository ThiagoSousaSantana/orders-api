package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerForm {

    private String name;
    private String email;
    private String phone;
    private List<AddressForm> address = new ArrayList<>();

    public Customer toCustomer(){
        var customer = new Customer(this.name, this.email, this.phone);
        var address = this.address.stream().map(addressForm ->
                addressForm.toAddress(customer)).collect(Collectors.toList());

        customer.setAddress(address);
        return customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AddressForm> getAddress() {
        return address;
    }

    public void setAddress(List<AddressForm> address) {
        this.address = address;
    }
}
