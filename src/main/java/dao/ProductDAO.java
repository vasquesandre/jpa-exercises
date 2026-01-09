package dao;

import domain.Product;
import jakarta.persistence.EntityManager;

public class ProductDAO extends DAO<Product> {
    public ProductDAO(EntityManager em) {
        super(em, Product.class);
    }
}
