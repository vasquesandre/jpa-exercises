package dao;

import domain.Order;
import jakarta.persistence.EntityManager;

public class OrderDAO extends DAO<Order> {
    public OrderDAO(EntityManager em) {
        super(em, Order.class);
    }
}
