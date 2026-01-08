package transactions.product;

import dao.ProductDAO;
import model.Product;

public class NewProduct {
    public static void main(String[] args) {
        Product product = new Product("iPhone", 1100.99);
        ProductDAO dao = new ProductDAO();
        dao.includeFull(product).close();
    }
}
