package transactions.user;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

public class NewUser {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = new User("Nicole", "nicoleoliveira@outlook.com");
        dao.includeFull(user);
        dao.printUser(user);
    }
}
