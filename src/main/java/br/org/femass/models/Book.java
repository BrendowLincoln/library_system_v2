package br.org.femass.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Author> authors = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Copy> copies = new ArrayList<>();

    public Book() { }

    public Book(Long id, String title, List<Author> authors, List<Copy> copies) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.copies = copies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Copy> getCopies() {
        return copies;
    }
}
