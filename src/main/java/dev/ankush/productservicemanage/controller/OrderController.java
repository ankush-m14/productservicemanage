package dev.ankush.productservicemanage.controller;


import dev.ankush.productservicemanage.models.Orders;
import dev.ankush.productservicemanage.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public void creatOrder(@RequestBody Orders order){
        orderService.creatOrder(order);
    }
}
