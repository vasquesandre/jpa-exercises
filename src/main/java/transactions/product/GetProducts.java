package transactions.product;

import dao.DAO;
import dao.ProductDAO;
import model.Product;

import java.util.List;

public class GetProducts {
    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();
        List<Product> products = dao.getAll(10, 0);

        for (Product product : products) {
            dao.printProduct(product);
        }

    }
}
