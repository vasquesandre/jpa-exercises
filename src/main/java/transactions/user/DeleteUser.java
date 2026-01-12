package transactions.user;

import dao.UserDAO;
import service.UserService;

public class DeleteUser {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.delete(4L);
    }
}
