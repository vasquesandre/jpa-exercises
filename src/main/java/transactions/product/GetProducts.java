package transactions.product;

import dao.DAO;
import model.Product;

import java.util.List;

public class GetProducts {
    public static void main(String[] args) {

        DAO<Product> dao = new DAO<>(Product.class);
        List<Product> products = dao.getAll(10, 0);

        for (Product product : products) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
        }

    }
}
