package br.org.femass.models.read;

import br.org.femass.utils.enums.Nationality;

import java.math.BigInteger;

public class AuthorCardModel {

    private Long id;
    private String firstName;
    private String secondName;
    private Nationality nationality;
    private BigInteger registeredBooksCount;

    public AuthorCardModel(Long id, String firstName, String secondName, Nationality nationality, BigInteger registeredBooksCount) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.nationality = nationality;
        this.registeredBooksCount = registeredBooksCount;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public Nationality getNationality() {
        return this.nationality;
    }

    public Long getId() {
        return this.id;
    }

    public BigInteger getRegisteredBooksCount() {
        return this.registeredBooksCount;
    }
}
