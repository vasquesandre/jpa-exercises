package service;

import dao.OrderDAO;
import domain.Order;
import domain.OrderProduct;
import domain.Product;
import domain.User;
import infra.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class OrderService {

    public List<Order> get(int limit, int offset) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            OrderDAO dao = new OrderDAO(em);
            return dao.getAll(limit, offset);
        } finally {
            em.close();
        }
    }

    public Order create(Long userId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            Order order = new Order();
            order.setUser(user);
            em.persist(order);
            em.getTransaction().commit();
            return order;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void addItem(Long orderId, Long productId, int quantity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Order order = em.find(Order.class, orderId);
            Product product = em.find(Product.class, productId);

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(quantity);
            orderProduct.setPrice(product.getPrice() * quantity);

            order.getItems().add(orderProduct);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }  finally {
            em.close();
        }
    }

}
