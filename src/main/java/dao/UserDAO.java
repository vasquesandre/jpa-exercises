package dao;

import domain.User;
import jakarta.persistence.EntityManager;

public class UserDAO extends DAO<User>{

    public UserDAO(EntityManager em) {
        super(em, User.class);
    }

}
