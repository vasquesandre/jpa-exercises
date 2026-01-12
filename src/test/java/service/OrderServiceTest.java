package service;

import domain.Order;
import domain.OrderProduct;
import domain.Product;
import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;
    private UserService userService;
    private ProductService productService;

    @BeforeEach
    void setup() {
        orderService = new OrderService();
        userService = new UserService();
        productService = new ProductService();
    }

    @Test
    void shouldCreateOrderForUser() {
        User user = userService.create(new User("Andre", "andre@email.com"));

        Order order = orderService.create(user.getId());

        assertNotNull(order.getId());
        assertEquals(user.getId(), order.getUser().getId());
    }

    @Test
    void shouldAddItemToOrder() {
        User user = userService.create(new User("Andre", "andre@email.com"));
        Product product = productService.create(new Product("iPhone", 1100.99));

        Order order = orderService.create(user.getId());

        orderService.addItem(order.getId(), product.getId(), 2);

        Order persistedOrder = orderService.get(order.getId());

        assertEquals(1, persistedOrder.getItems().size());

        OrderProduct item = persistedOrder.getItems().get(0);
        assertEquals(2, item.getQuantity());
        assertEquals(2201.98, item.getPrice());
    }

    @Test
    void shouldNotAllowZeroQuantity() {
        User user = userService.create(new User("Andre", "andre@email.com"));
        Product product = productService.create(new Product("iPhone", 1100.99));

        Order order = orderService.create(user.getId());

        assertThrows(IllegalArgumentException.class, () ->
                orderService.addItem(order.getId(), product.getId(), 0)
        );
    }

    @Test
    void shouldUpdateQuantityWhenAddingSameProductAgain() {
        User user = userService.create(new User("Andre", "andre@email.com"));
        Product product = productService.create(new Product("iPhone", 1100.99));

        Order order = orderService.create(user.getId());

        orderService.addItem(order.getId(), product.getId(), 2);
        orderService.addItem(order.getId(), product.getId(), 1);

        Order persistedOrder = orderService.get(order.getId());

        assertEquals(1, persistedOrder.getItems().size());
        assertEquals(3, persistedOrder.getItems().get(0).getQuantity());
    }

    @Test
    void newOrderShouldStartWithoutItems() {
        User user = userService.create(new User("Andre", "andre@email.com"));

        Order order = orderService.create(user.getId());

        Order persistedOrder = orderService.get(order.getId());

        assertTrue(persistedOrder.getItems().isEmpty());
    }

    @Test
    void shouldNotAddItemToNonExistingOrder() {
        Product product = productService.create(new Product("iPhone", 1100.99));

        assertThrows(IllegalArgumentException.class, () ->
                orderService.addItem(9999L, product.getId(), 1)
        );
    }

    @Test
    void shouldNotAddNonExistingProductToOrder() {
        User user = userService.create(new User("Andre", "andre@email.com"));
        Order order = orderService.create(user.getId());

        assertThrows(IllegalArgumentException.class, () ->
                orderService.addItem(order.getId(), 9999L, 1)
        );
    }

    @Test
    void shouldRemoveItemFromOrder() {
        User user = userService.create(new User("Andre", "andre@email.com"));
        Product product = productService.create(new Product("iPhone", 1100.99));

        Order order = orderService.create(user.getId());
        orderService.addItem(order.getId(), product.getId(), 2);

        Order persistedOrder = orderService.get(order.getId());
        OrderProduct item = persistedOrder.getItems().get(0);

        orderService.removeItem(order.getId(), item.getProduct().getId());

        Order updatedOrder = orderService.get(order.getId());
        assertTrue(updatedOrder.getItems().isEmpty());
    }

}