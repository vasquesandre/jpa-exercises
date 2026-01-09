package transactions.product;

import dao.ProductDAO;
import domain.Product;
import service.ProductService;

import java.util.List;

public class GetProducts {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        List<Product> products = productService.get(10, 0);
        products.forEach(System.out::println);
    }
}
