package dev.ankush.productservicemanage.services;

import dev.ankush.productservicemanage.models.Orders;
import dev.ankush.productservicemanage.thirdpartyclients.fakestore.SelfOrderServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service("SelfOrderService")
public class SelfOrderService implements OrderService{
    SelfOrderServiceClient selfOrderServiceClient;
    @Autowired
    public SelfOrderService(SelfOrderServiceClient selfOrderServiceClient) {
        this.selfOrderServiceClient = selfOrderServiceClient;
    }

    @Override
    public void creatOrder(Orders order) {
        selfOrderServiceClient.creatOrder(order);
    }
}
