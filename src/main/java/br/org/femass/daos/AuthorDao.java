package br.org.femass.daos;

import br.org.femass.models.Author;
import br.org.femass.utils.queries.AuthorQueries;

import java.math.BigInteger;
import java.util.List;

public class AuthorDao extends Dao<Author> {
    public List<Author> getAll() {
        return em.createQuery(AuthorQueries.GET_ALL_AUTHORS).getResultList();
    }

    public List<Author> getByFilter(String filter) {
        var query = em.createQuery(AuthorQueries.GET_AUTHORS_BY_FILTER);

        query.setParameter("filter", "%" + filter.toLowerCase() + "%");

        return query.getResultList();
    }

    public BigInteger getRegisteredBooksByAuthorId(Long id) {
        var query = em.createNativeQuery(AuthorQueries.GET_REGISTERED_BOOKS_COUNT_BY_AUTHOR);

        query.setParameter("id", id);

        return (BigInteger) query.getSingleResult();
    }
}
