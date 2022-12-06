package br.org.femass.daos;

import br.org.femass.models.Reader;
import br.org.femass.utils.queries.AuthorQueries;
import br.org.femass.utils.queries.BookQueries;
import br.org.femass.utils.queries.ReaderQueries;

import java.util.List;

public class ReaderDao extends Dao<Reader> {
    public List<Reader> getAllStudents() {
        return em.createQuery(ReaderQueries.GET_ALL_STUDENTS).getResultList();
    }

    public List<Reader> getAllTeachers() {
        return em.createQuery(ReaderQueries.GET_ALL_TEACHERS).getResultList();
    }


    public List<Reader> getStudentsByFilter(String filter) {
        var query = em.createQuery(ReaderQueries.GET_STUDENTS_BY_FILTER);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");

        return query.getResultList();
    }

    public List<Reader> getTeachersByFilter(String filter) {
        var query = em.createQuery(ReaderQueries.GET_TEACHERS_BY_FILTER);
        query.setParameter("filter", "%" + filter.toLowerCase() + "%");

        return query.getResultList();
    }
}
