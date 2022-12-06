package br.org.femass.models;

import br.org.femass.utils.enums.Country;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetName;
    private Long number;
    private String neighborhood;
    private String city;
    private String state;
    private Country country;

    public Address() { }

    public Address(String streetName, Long number, String neighborhood, String city, String state, Country country) {
        this.streetName = streetName;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Long getId() {
        return this.id;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public Long getNumber() {
        return this.number;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public Country getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return this.streetName + ", " + this.number.toString() + ", " + this.city + "/" + this.state + " - " + this.country.toString();
    }
}
