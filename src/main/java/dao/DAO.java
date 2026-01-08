package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DAO<T> {

    private static EntityManagerFactory emf;
    protected EntityManager em;
    private Class<T> clazz;

    static{
        try {
            emf = Persistence.createEntityManagerFactory("jpa_exercises");
        } catch (Exception e) {

        }
    }

    public DAO() {
        this(null);
    }

    public DAO(Class<T> clazz) {
        this.clazz = clazz;
        em = emf.createEntityManager();
    }

    public DAO<T> openTransaction() {
        em.getTransaction().begin();
        return this;
    }

    public DAO<T> closeTransaction() {
        em.getTransaction().commit();
        return this;
    }

    public DAO<T> include(T entity) {
        em.persist(entity);
        return this;
    }

    public DAO<T> includeFull(T entity) {
        return this.openTransaction().include(entity).closeTransaction();
    }

    public List<T> getAll(int limit, int offset) {
        if(clazz == null){
            throw new UnsupportedOperationException("Class is null");
        }

        String jpql = "select e from " + clazz.getName() + " e";
        TypedQuery<T> query = em.createQuery(jpql, clazz);
        query.setMaxResults(limit).setFirstResult(offset);

        return query.getResultList();
    }

    public void close() {
        em.close();
    }
}
