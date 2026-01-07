import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

public class UpdateUser {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exercises");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User user = em.find(User.class, 2);
        user.setEmail("nicoleoliveira@outlook.com");
        em.merge(user);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
