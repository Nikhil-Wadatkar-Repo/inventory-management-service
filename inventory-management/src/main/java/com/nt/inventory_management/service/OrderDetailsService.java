package com.nt.inventory_management.service;

import com.nt.inventory_management.dto.ItemDTO;
import com.nt.inventory_management.entity.Item;
import com.nt.inventory_management.entity.OrderDetails;
import com.nt.inventory_management.repo.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class OrderDetailsService {

    private final OrderRepository orderRepo;

    @Autowired
    public OrderDetailsService(OrderRepository OrderDetailsRepository) {
        this.orderRepo = OrderDetailsRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return orderRepo.findAll();
    }

    public OrderDetails getOrderDetailsById(Long OrderDetailsId) {
        return orderRepo.findById(OrderDetailsId)
                .orElseThrow(() -> new IllegalArgumentException("OrderDetails not found with id " + OrderDetailsId));
    }

    public OrderDetails saveOrderDetails(OrderDetails OrderDetails) {
        return orderRepo.save(OrderDetails);
    }

    public void deleteOrderDetails(Long OrderDetailsId) {
        orderRepo.deleteById(OrderDetailsId);
    }

    public OrderDetails addItemToExistedOrder(ItemDTO itemDTO) {
        OrderDetails orderDetails = getOrderDetailsById(itemDTO.getOrderId());
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO.getItemDetails(), item);
        orderDetails.getOrderedItems().add(item);
        OrderDetails orderDetails1 = orderRepo.save(orderDetails);
        return orderDetails1;
    }

    public String deleteItemToExistedOrder(Long itemId, Long orderId) {
        String message = "Not deleted";
        OrderDetails orderDetails = getOrderDetailsById(orderId);
        List<Item> orderedItems = orderDetails.getOrderedItems();

        Iterator<Item> iterator = orderedItems.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getItemId() == itemId) {
                iterator.remove();
                message = "Deleted Successfully";
                break;

            }
        }
        return message;
    }
}

