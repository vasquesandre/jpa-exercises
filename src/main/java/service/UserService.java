package service;

import dao.UserDAO;
import domain.User;
import infra.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserService {

    public List<User> get(int limit, int offset) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            UserDAO dao = new UserDAO(em);
            return dao.getAll(limit, offset);
        } finally {
            em.close();
        }
    }

    public void create(User user) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            UserDAO dao = new UserDAO(em);
            dao.include(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public User updateEmailById(int id, String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            UserDAO dao = new UserDAO(em);
            User user = dao.findById(id);
            user.setEmail(email);
            dao.merge(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            UserDAO dao = new UserDAO(em);
            User user = dao.findById(id);
            dao.delete(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }  finally {
            em.close();
        }
    }
}
