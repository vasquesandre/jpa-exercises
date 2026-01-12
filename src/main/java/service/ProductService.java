package service;

import dao.DAO;
import dao.ProductDAO;
import domain.Product;
import infra.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductService {

    public List<Product> get(int limit, int offset) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            DAO<Product> dao = new DAO<>(em, Product.class);
            return dao.getAll(limit, offset);
        } finally {
            em.close();
        }
    }

    public Product get(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ProductDAO dao = new ProductDAO(em);
            return dao.findById(id);
        } finally {
            em.close();
        }
    }

    public Product create(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            ProductDAO dao = new ProductDAO(em);
            dao.include(product);
            em.getTransaction().commit();
            return dao.findById(product.getId());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Product updatePriceById(int id, double price) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            ProductDAO dao = new ProductDAO(em);
            Product product = dao.findById(id);
            product.setPrice(price);
            Product updated = dao.merge(product);
            em.getTransaction().commit();
            return updated;
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

            List<?> orderProducts = em.createQuery(
                    "select op from OrderProduct op where op.product.id = :id")
                    .setParameter("id", id)
                    .getResultList();

            if (!orderProducts.isEmpty()) {
                throw new IllegalStateException("Product is already used in an order");
            }

            ProductDAO dao = new ProductDAO(em);
            Product product = dao.findById(id);
            dao.delete(product);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
