package br.org.femass.models;

import javax.persistence.Entity;

@Entity
public class Student extends Reader {
    private String register;

    public Student() { }

    public Student(String name, Address address, Telephone telephone, String register) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.deadlineForReturn = 15;
        this.register = register;
    }

    public Student(Long id, String name, Address address, Telephone telephone, String register) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.deadlineForReturn = 15;
        this.register = register;
    }

    public String getRegister() {
        return register;
    }
}
