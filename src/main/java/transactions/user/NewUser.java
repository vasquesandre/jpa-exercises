package transactions.user;

import dao.UserDAO;
import domain.User;
import service.UserService;

public class NewUser {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User("Nicole", "nicoleoliveira@outlook.com");
        userService.create(user);
    }
}
