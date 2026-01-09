package transactions.user;

import dao.UserDAO;
import domain.User;

public class UpdateUser {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = dao.updateUserEmailById(2, "nicoleoliveira@gmail.com");
        dao.printUser(user);
    }
}
