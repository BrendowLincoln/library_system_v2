package br.org.femass.models;

import javax.persistence.Entity;

@Entity
public class Teacher extends Reader {
    private String subject;

    public Teacher() { }

    public Teacher(String name, Address address, Telephone telephone, String subject) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.deadlineForReturn = 30;
        this.subject = subject;
    }

    public Teacher(Long id, String name, Address address, Telephone telephone, String subject) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.deadlineForReturn = 30;
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    @Override
    public String toString() {
        return this.name +" (" + this.readerType() +")";
    }
}
