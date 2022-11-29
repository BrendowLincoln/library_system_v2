package br.org.femass.models;

import br.org.femass.utils.enums.Nationality;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String secondName;
    @Enumerated(EnumType.ORDINAL)
    private Nationality nationality;

    public Author() { }

    public Author(String name, String passwrord, Nationality nationality) {
        this.name = name;
        this.secondName = name;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public Nationality getNationality() {
        return nationality;
    }
}
