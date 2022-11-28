package br.org.femass.models;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Employee() { }

    public Employee(String name, User user) {
        this.name = name;
        this.user = user;

    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " (" + this.user.getRole() + ")";
    }
}
