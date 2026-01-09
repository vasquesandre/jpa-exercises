package transactions.product;

import dao.ProductDAO;
import domain.Product;
import service.ProductService;

public class UpdateProduct {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = productService.updateValueById(2, 999.90);
        System.out.println(product);
    }
}
