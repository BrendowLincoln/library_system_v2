package br.org.femass.daos;

import br.org.femass.models.Loan;
import br.org.femass.utils.queries.LoanQueries;

import java.util.List;

public class LoanDao extends Dao<Loan> {

    public List<Loan> getAll() {
        return em.createQuery(LoanQueries.GET_ALL_LOANS).getResultList();
    }

    public List<Loan> getByFilter(String filter) {
        var query = em.createQuery(LoanQueries.GET_LOANS_BY_FILTER);

        query.setParameter("filter", "%" + filter.toLowerCase() + "%");

        return query.getResultList();
    }

    public List<Loan> getThreeMostRecently() {
        return em.createNativeQuery(LoanQueries.GET_THREE_MOST_RECENTLY).getResultList();
    }

}
