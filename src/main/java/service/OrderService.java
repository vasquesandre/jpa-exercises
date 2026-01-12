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
            return em.createQuery(
                            "select distinct o from Order o " +
                                    "left join fetch o.items " +
                                    "left join fetch o.user",
                            Order.class
                    )
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Order get(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "select o from Order o " +
                                    "left join fetch o.items " +
                                    "left join fetch o.user " +
                                    "where o.id = :id",
                            Order.class
                    )
                    .setParameter("id", id)
                    .getSingleResult();
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
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            Order order = em.find(Order.class, orderId);
            Product product = em.find(Product.class, productId);

            OrderProduct existingItem = null;

            for (OrderProduct item : order.getItems()) {
                if (item.getProduct().getId().equals(productId)) {
                    existingItem = item;
                    break;
                }
            }

            if (existingItem != null) {
                int newQuantity = existingItem.getQuantity() + quantity;
                existingItem.setQuantity(newQuantity);
                existingItem.setPrice(product.getPrice() * newQuantity);
            } else {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setOrder(order);
                orderProduct.setProduct(product);
                orderProduct.setQuantity(quantity);
                orderProduct.setPrice(product.getPrice() * quantity);

                order.getItems().add(orderProduct);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void removeItem(Long orderId, Long productId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, orderId);

            OrderProduct existingItem = null;

            for (OrderProduct item : order.getItems()) {
                if (item.getProduct().getId().equals(productId)) {
                    existingItem = item;
                    break;
                }
            }

            if (existingItem != null) {
                order.getItems().remove(existingItem);
                em.getTransaction().commit();
            } else {
                throw new IllegalArgumentException("Product not found on orderId: " + orderId);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }  finally {
            em.close();
        }
    }

}
