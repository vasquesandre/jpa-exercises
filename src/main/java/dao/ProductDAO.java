package dao;

import model.Product;

public class ProductDAO extends DAO<Product> {
    public ProductDAO() {
        super(Product.class);
    }

    public void printProduct(Product product) {
        System.out.println("ID: " + product.getId() + " || Name: " + product.getName() + " || Price: " + product.getPrice());
    }

    public Product updateProductValueById(int id, double price) {
        Product product = em.find(Product.class, id);
        product.setPrice(price);
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
        return product;
    }
}
