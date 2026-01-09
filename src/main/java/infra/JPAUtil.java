package infra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpa_exercises");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
