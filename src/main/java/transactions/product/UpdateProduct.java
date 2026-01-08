package transactions.product;

import dao.ProductDAO;
import model.Product;

public class UpdateProduct {

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Product product = dao.updateProductValueById(2, 1100.99);
        dao.printProduct(product);
    }
}
