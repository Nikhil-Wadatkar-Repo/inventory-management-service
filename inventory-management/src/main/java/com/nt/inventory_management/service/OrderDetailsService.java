package com.nt.inventory_management.service;

import com.nt.inventory_management.dto.ItemList;
import com.nt.inventory_management.dto.OrderDTO;
import com.nt.inventory_management.entity.Item;
import com.nt.inventory_management.entity.OrderDetails;
import com.nt.inventory_management.entity.OrderedItemDetails;
import com.nt.inventory_management.repo.ItemRepository;
import com.nt.inventory_management.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private  OrderRepository orderRepo;

    @Autowired
    private ItemRepository itemRepository;


    public List<OrderDetails> getAllOrderDetails() {
        return orderRepo.findAll();
    }

    public OrderDetails getOrderDetailsById(Long OrderDetailsId) {
        return orderRepo.findById(OrderDetailsId)
                .orElseThrow(() -> new IllegalArgumentException("OrderDetails not found with id " + OrderDetailsId));
    }

    public OrderDetails saveOrderDetails(OrderDTO orderDTO) {
        List<Item> dbItems=itemRepository.findAll();
        List<ItemList> itemLists=orderDTO.getItemLists();
        List<OrderedItemDetails> orderedItems=new LinkedList<>();
        OrderDetails orderDetails= OrderDetails.
                builder().
                workerName(orderDTO.getWorkerName()) .
                customerName(orderDTO.getCustomerName()).
                build();
        //get each item code and quantity and search it in dbItems
        itemLists.stream().map(itemDetail->dbItems.contains(itemDetail)).

        return orderRepo.save(orderDetails);
    }

    public void deleteOrderDetails(Long OrderDetailsId) {
        orderRepo.deleteById(OrderDetailsId);
    }

//    public OrderDetails addItemToExistedOrder(ItemDTO itemDTO) {
//        OrderDetails orderDetails = getOrderDetailsById(itemDTO.getOrderId());
//        Item item = new Item();
//        BeanUtils.copyProperties(itemDTO.getItemDetails(), item);
////        orderDetails.getOrderedItems().add(item);
//        OrderDetails orderDetails1 = orderRepo.save(orderDetails);
//        return orderDetails1;
//    }
//
//    public String deleteItemToExistedOrder(Long itemId, Long orderId) {
//        String message = "Not deleted";
//        OrderDetails orderDetails = getOrderDetailsById(orderId);
////        List<Item> orderedItems = orderDetails.getOrderedItems();
////
////        Iterator<Item> iterator = orderedItems.iterator();
////        while (iterator.hasNext()) {
////            Item item = iterator.next();
////            if (item.getItemId() == itemId) {
////                iterator.remove();
////                message = "Deleted Successfully";
////                break;
////
////            }
////        }
//        return message;
//    }
}

