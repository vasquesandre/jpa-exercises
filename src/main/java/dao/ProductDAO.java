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
        Product product = findById(id);
        product.setPrice(price);
        return merge(product);
    }
}
