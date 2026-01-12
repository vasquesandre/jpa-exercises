package service;

import domain.Order;
import domain.Product;
import domain.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void shouldCreateProduct() {
        ProductService productService = new ProductService();

        Product product = productService.create(
                new Product("Notebook", 3500.00)
        );

        assertNotNull(product.getId());
        assertEquals("Notebook", product.getName());
        assertEquals(3500.00, product.getPrice());
    }

    @Test
    void shouldGetProductById() {
        ProductService productService = new ProductService();

        Product created = productService.create(
                new Product("Mouse", 150.00)
        );

        Product found = productService.get(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
    }

    @Test
    void shouldUpdateProductPrice() {
        ProductService productService = new ProductService();

        Product product = productService.create(
                new Product("Keyboard", 200.00)
        );

        Product updated = productService.updatePriceById(product.getId(), 250.00);

        assertEquals(250.00, updated.getPrice());
    }

    @Test
    void shouldGetProductsWithLimitAndOffset() {
        ProductService productService = new ProductService();

        productService.create(new Product("P1", 10.0));
        productService.create(new Product("P2", 20.0));
        productService.create(new Product("P3", 30.0));

        List<Product> products = productService.get(2, 0);

        assertEquals(2, products.size());
    }

    @Test
    void shouldNotDeleteProductUsedInOrder() {
        ProductService productService = new ProductService();
        UserService userService = new UserService();
        OrderService orderService = new OrderService();

        User user = userService.create(
                new User("Andre", "andre@email.com")
        );

        Product product = productService.create(
                new Product("iPhone", 5000.00)
        );

        Order order = orderService.create(user.getId());
        orderService.addItem(order.getId(), product.getId(), 1);

        assertThrows(IllegalStateException.class, () ->
                productService.delete(product.getId())
        );
    }

    @Test
    void shouldDeleteProductNotUsedInOrder() {
        ProductService productService = new ProductService();

        Product product = productService.create(
                new Product("Headphone", 300.00)
        );

        productService.delete(product.getId());

        Product deleted = productService.get(product.getId());
        assertNull(deleted);
    }

}