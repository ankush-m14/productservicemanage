package dev.ankush.productservicemanage.repositories;

import dev.ankush.productservicemanage.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
}
