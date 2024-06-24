package com.nt.inventory_management.repo;

import com.nt.inventory_management.entity.OrderDetails;
import com.nt.inventory_management.entity.OrderedItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderedItemDetails, Long> {
}
