package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Address;
import br.com.thiagosousa.ordersapi.model.Customer;

public class AddressForm {

    private String number;
    private String street;
    private String city;
    private String postcode;
    private String country;

    public Address toAddress(Customer customer){
        var address = new Address(this.number, this.street, this.city, this.postcode, this.country);
        address.setCustomer(customer);
        return address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
