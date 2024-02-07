package dev.ankush.productservicemanage.thirdpartyclients.fakestore;

import dev.ankush.productservicemanage.models.Orders;
import dev.ankush.productservicemanage.models.Product;
import dev.ankush.productservicemanage.repositories.OrderRepository;
import dev.ankush.productservicemanage.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelfOrderServiceClient {
    OrderRepository orderRepository;

    @Autowired
    public SelfOrderServiceClient(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void creatOrder(Orders order) {
        List<Product> productList = order.getProductList();
        if(productList != null) {
            for(Product product : productList) {
                product.getOrders().add(order);
            }
        }
        orderRepository.save(order);
    }
}
