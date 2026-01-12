package infra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DatabaseCleaner {
    public static void clean() {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            em.createNativeQuery("DELETE FROM order_product").executeUpdate();
            em.createNativeQuery("DELETE FROM orders").executeUpdate();
            em.createNativeQuery("DELETE FROM products").executeUpdate();
            em.createNativeQuery("DELETE FROM users").executeUpdate();

            tx.commit();
        } finally {
            em.close();
        }
    }
}
