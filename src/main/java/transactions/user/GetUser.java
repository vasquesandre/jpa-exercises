package transactions.user;

import dao.UserDAO;
import domain.User;

public class GetUser {

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.findById(1);
        dao.printUser(user);
    }
}
