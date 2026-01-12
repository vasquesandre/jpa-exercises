package transactions.product;

import domain.Product;
import service.ProductService;

public class UpdateProduct {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        Product product = productService.updatePriceById(2L, 749.49);
        System.out.println(product);
    }
}
