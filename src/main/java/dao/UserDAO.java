package dao;

import domain.User;
import jakarta.persistence.EntityManager;

public class UserDAO extends DAO<User>{

    public UserDAO(EntityManager em) {
        super(em, User.class);
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
