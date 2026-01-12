package dao;

import jakarta.persistence.EntityManager;

import java.util.List;

public class DAO<T> {

    protected EntityManager em;
    private final Class<T> clazz;

    public DAO(EntityManager em, Class<T> clazz) {
        this.clazz = clazz;
        this.em = em;
    }

    public void include(T entity) {
        em.persist(entity);
    }

    public T merge(T entity) {
        return em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public T findById(Object id) {
        return em.find(clazz, id);
    }

    public List<T> getAll(int limit, int offset) {
        return em.createQuery("from " + clazz.getName(), clazz)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();
    }
}
