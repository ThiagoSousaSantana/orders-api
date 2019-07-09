package br.com.thiagosousa.ordersapi.controller.dto;

import br.com.thiagosousa.ordersapi.model.Address;

public class AddressDto {

    private String number;
    private String street;
    private String city;
    private String postcode;
    private String country;

    public AddressDto(Address address) {
        this.number = address.getNumber();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postcode = address.getPostcode();
        this.country = address.getCountry();
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }
}
