package br.org.femass.models.read;

import br.org.femass.models.Author;
import br.org.femass.models.Copy;

import java.util.List;

public class BookCardModel {
    private Long id;
    private String title;
    private List<Author> authors;
    private Integer registeredCopiesCount;

    public BookCardModel(Long id, String title, List<Author> authors, Integer registeredCopiesCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.registeredCopiesCount = registeredCopiesCount;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public Integer getRegisteredCopiesCount() {
        return this.registeredCopiesCount;
    }
}
