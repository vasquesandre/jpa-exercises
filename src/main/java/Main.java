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

//        List<Order> order = orderService.get(10,0);
//        order.forEach(System.out::println);

        Order order = orderService.get(15L);
        System.out.println(order);






    }
}
