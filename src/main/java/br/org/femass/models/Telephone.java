package br.org.femass.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer areaCode;
    private Long number;

    public Telephone() { }

    public Telephone(Integer areaCode, Long number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public Long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "(" + this.areaCode.toString() + ") " + this.number.toString();
    }
}
