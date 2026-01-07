import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

public class DeleteUser {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exercises");
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, 2);

        if(user != null){
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }

        em.close();
        emf.close();
    }
}
