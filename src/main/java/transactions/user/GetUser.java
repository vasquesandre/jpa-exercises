package transactions.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

import java.util.List;

public class GetUser {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exercises");
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, 1);
        System.out.println(user.getUsername());

        List<User> users = em.createQuery("from User").getResultList();
        for (User u : users) {
            System.out.println(u.getUsername());
        }

        em.close();
        emf.close();
    }
}
