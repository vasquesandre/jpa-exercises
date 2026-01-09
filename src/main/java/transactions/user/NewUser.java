package transactions.user;

import dao.UserDAO;
import domain.User;

public class NewUser {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        User user = new User("Nicole", "nicoleoliveira@outlook.com");
        dao.includeFull(user);
        dao.printUser(user);
    }
}
