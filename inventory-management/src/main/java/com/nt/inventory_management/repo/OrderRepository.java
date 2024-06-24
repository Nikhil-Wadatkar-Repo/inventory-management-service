package com.nt.inventory_management.repo;

import com.nt.inventory_management.entity.Item;
import com.nt.inventory_management.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
}
