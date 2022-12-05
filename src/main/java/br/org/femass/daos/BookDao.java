package br.org.femass.daos;

import br.org.femass.models.Book;
import br.org.femass.utils.queries.AuthorQueries;
import br.org.femass.utils.queries.BookQueries;

import java.util.List;

public class BookDao extends Dao<Book> {
    public List<Book> getAll() {
        return em.createQuery(BookQueries.GET_ALL_BOOKS).getResultList();
    }

    public List<Book> getByFilter(String filter) {
        var query = em.createQuery(BookQueries.GET_BOOKS_BY_FILTER);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");

        return query.getResultList();
    }

}
