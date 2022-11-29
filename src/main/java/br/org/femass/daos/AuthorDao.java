package br.org.femass.daos;

import br.org.femass.models.Author;
import br.org.femass.models.Employee;
import br.org.femass.utils.queires.AuthorQueries;

import java.util.List;

public class AuthorDao extends Dao<Author> {
    public List<Author> getAll() {
        return em.createQuery(AuthorQueries.GET_ALL_AUTHORS).getResultList();
    }
}
