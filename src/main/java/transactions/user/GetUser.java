package transactions.user;

import dao.UserDAO;
import domain.User;
import service.UserService;

import java.util.List;

public class GetUser {

    public static void main(String[] args) {
        UserService userService = new UserService();
        List<User> users =  userService.get(10, 0);
        users.forEach(System.out::println);
    }
}
