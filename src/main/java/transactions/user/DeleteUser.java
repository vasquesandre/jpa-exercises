package transactions.user;

import dao.UserDAO;

public class DeleteUser {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        dao.deleteUserById(2);
    }
}
