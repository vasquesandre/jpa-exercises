package transactions.user;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

public class DeleteUser {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        dao.deleteUserById(2);
    }
}
