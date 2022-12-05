package br.org.femass.daos;

import br.org.femass.models.Book;
import br.org.femass.models.Copy;
import br.org.femass.utils.queries.BookQueries;

import java.util.List;

public class CopyDao extends Dao<Copy> {
    public List<Copy> getCopiesByBookId(Long id) {
        var query = em.createQuery(BookQueries.GET_BOOKS_BY_FILTER);
        query.setParameter("bookId", "%" +  id + "%");

        return query.getResultList();
    }
}
