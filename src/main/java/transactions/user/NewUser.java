package transactions.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

public class NewUser {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exercises");
        EntityManager em = emf.createEntityManager();

        User user = new User("Nicole", "nicolevasques@outlook.com");

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
