package transactions.user;

import dao.UserDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;

import java.util.List;

public class GetUser {

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.findById(1);
        dao.printUser(user);
    }
}
