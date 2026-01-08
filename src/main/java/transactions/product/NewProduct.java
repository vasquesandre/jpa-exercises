package transactions.product;

import dao.DAO;
import model.Product;

public class NewProduct {

    public static void main(String[] args) {

        Product product = new Product("iPhone", 1100.99);

        DAO<Product> dao = new DAO<>(Product.class);
        dao.includeFull(product).close();

    }
}
