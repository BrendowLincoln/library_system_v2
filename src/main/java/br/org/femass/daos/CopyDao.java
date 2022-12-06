package br.org.femass.daos;

import br.org.femass.models.Book;
import br.org.femass.models.Copy;
import br.org.femass.utils.queries.BookQueries;
import br.org.femass.utils.queries.CopyQueries;

import java.util.List;

public class CopyDao extends Dao<Copy> {
    public List<Copy> getAll() {
        return  em.createQuery(CopyQueries.GET_ALL_COPIES).getResultList();
    }

    public List<Copy> getAvailableCopies() {
        return em.createQuery(CopyQueries.GET_AVAILABLE_COPIES).getResultList();
    }
}
