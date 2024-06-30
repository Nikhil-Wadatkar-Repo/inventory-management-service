package com.nt.inventory_management.repo;

import com.nt.inventory_management.dto.OrderList;
import com.nt.inventory_management.entity.Item;
import com.nt.inventory_management.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
String query="select ol.order_Id as orderId, ol.final_Price as finalPrice,ol.created_Date as createdDate,ol.created_By as createdBy,ol.customer_Name as customerName,ol.worker_Name as workerName from order_details ol";
    @Query(value = query,nativeQuery = true)
    List<OrderList> getOrderList();
}
