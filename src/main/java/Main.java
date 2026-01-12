import domain.Order;
import domain.OrderProduct;
import domain.Product;
import domain.User;
import service.OrderService;
import service.ProductService;
import service.UserService;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        UserService userService = new UserService();
        ProductService productService = new ProductService();

        User user = userService.get(5);

        Order order = orderService.create(user.getId());

        Product product = productService.get(3);

        orderService.addItem(order.getId(), product.getId(), 1);

    }
}
