package transactions.user;

import dao.UserDAO;
import domain.User;
import service.UserService;

public class UpdateUser {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = userService.updateEmailById(5, "nicoleoliveira@gmail.com");
        System.out.println(user);
    }
}
