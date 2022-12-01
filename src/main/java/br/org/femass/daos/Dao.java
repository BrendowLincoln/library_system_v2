package br.org.femass.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao<E> {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    static  {
        try {
            emf = Persistence.createEntityManagerFactory("jpa_library_system_v2");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Dao() {
        em = emf.createEntityManager();
    }

    public void save(E entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }


    public void delete(E entidade) {
        em.getTransaction().begin();
        em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
        em.getTransaction().commit();
    }

    public void change(E entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }
}
