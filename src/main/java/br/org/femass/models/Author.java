package br.org.femass.models;

import br.org.femass.utils.enums.Nationality;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    @Enumerated(EnumType.ORDINAL)
    private Nationality nationality;

    public Author() { }

    public Author(Long id, String firstName, String secondName, Nationality nationality) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.nationality = nationality;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Nationality getNationality() {
        return nationality;
    }


    @Override
    public String toString() {
        return this.firstName + " " + this.secondName;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        Author author = (Author) obj;
        return this.id.equals(author.id);
    }
}
