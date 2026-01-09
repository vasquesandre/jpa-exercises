package transactions.product;

import service.ProductService;

public class DeleteProduct {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        productService.delete(2);
    }
}
