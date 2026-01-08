package dao;

import model.User;

public class UserDAO extends DAO<User>{

    public UserDAO() {
        super(User.class);
    }

    public void printUser(User user){
        System.out.println("ID: " + user.getId() + " || Name: " + user.getUsername() + " || Email: " + user.getEmail());
    }

    public User updateUserEmailById(int id, String email){
        User user = findById(id);
        user.setEmail(email);
        return merge(user);
    }

    public void deleteUserById(int id){
        User user = findById(id);
        delete(user);
    }

}
