package br.org.femass.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate acquisitionDate;
    private Boolean isLoaned;
    @ManyToOne(cascade=CascadeType.ALL)
    private Book book;

    public Copy() { }

    public Copy(LocalDate acquisitionDate, Boolean isLoaned) {
        this.acquisitionDate = acquisitionDate;
        this.isLoaned = isLoaned;
    }

    public Copy(LocalDate acquisitionDate, Boolean isLoaned, Book book) {
        this.book = book;
        this.acquisitionDate = acquisitionDate;
        this.isLoaned = isLoaned;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public Boolean getIsLoaned() {
        return isLoaned;
    }

    public Book getBook() { return book; }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setIsLoaned(Boolean isLoaned) {
        this.isLoaned = isLoaned;
    }

    @Override
    public String toString() {
        return "#" + this.id + " - " + this.book.getTitle() + ": " + this.acquisitionDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
