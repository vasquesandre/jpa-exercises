package transactions.product;

import dao.ProductDAO;
import domain.Product;
import service.ProductService;

public class NewProduct {
    public static void main(String[] args) {
        Product product = new Product("PS5", 759.49);
        ProductService productService = new ProductService();
        productService.create(product);
    }
}
