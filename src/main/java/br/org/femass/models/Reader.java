package br.org.femass.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @OneToOne(cascade = CascadeType.ALL)
    protected Address address;
    @OneToOne(cascade = CascadeType.ALL)
    protected Telephone telephone;
    protected Integer deadlineForReturn;

    public Reader() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public Integer getDeadlineForReturn() {
        return deadlineForReturn;
    }
}
