package transactions.user;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

public class UpdateUser {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.updateUserEmailById(2, "nicoleoliveira@gmail.com");
        dao.printUser(user);
    }
}
