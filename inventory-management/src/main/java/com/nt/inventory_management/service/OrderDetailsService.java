package com.nt.inventory_management.service;

//import com.nt.inventory_management.dto.ItemList;

import com.nt.inventory_management.dto.CodeWisePrices;
import com.nt.inventory_management.dto.ItemListDTO;
import com.nt.inventory_management.dto.OrderDTO;
import com.nt.inventory_management.dto.OrderList;
import com.nt.inventory_management.entity.Item;
import com.nt.inventory_management.entity.OrderDetails;
import com.nt.inventory_management.entity.OrderedItemDetails;
import com.nt.inventory_management.repo.ItemRepository;
import com.nt.inventory_management.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ItemRepository itemRepository;


    public List<OrderList> getAllOrderDetails() {
        return orderRepo.getOrderList();
    }

    public OrderDetails getOrderDetailsById(Long OrderDetailsId) {
        return orderRepo.findById(OrderDetailsId)
                .orElseThrow(() -> new IllegalArgumentException("OrderDetails not found with id " + OrderDetailsId));
    }

    public OrderDetails saveOrderDetails(OrderDTO orderDTO) {
        List<OrderedItemDetails> orderedItems = new LinkedList<>();
        OrderDetails orderDetails = OrderDetails.
                builder().
                workerName(orderDTO.getWorkerName()).
                customerName(orderDTO.getCustomerName()).
                build();
        List<Item> dbItems = itemRepository.findAll();
        List<ItemListDTO> itemLists = orderDTO.getOrderedItems();
        List<String> itemCodes = itemLists.stream().map(item -> item.getItemCode() + "").collect(Collectors.toList());
        List<CodeWisePrices> allPricess = itemRepository.getAllPricess(itemCodes);
        int total = 0;
        for (int i = 0; i < itemLists.size(); i++) {
            Integer quantity = itemLists.get(i).getQuantity();
            Integer price = allPricess.get(i).getPrice();
            int rate = price * quantity;
            total+=rate;
            OrderedItemDetails orderedItemDetails = OrderedItemDetails
                    .builder()
                    .itemName(itemLists.get(i).getItemName())
                    .itemCode(itemLists.get(i).getItemCode())
                    .quantity(quantity)
                    .rate(rate)
                    .build();
            orderedItems.add(orderedItemDetails);
        }
        orderDetails.setOrderedItems(orderedItems);
        orderDetails.setFinalPrice((long) total);
        orderDetails.setCreatedDate(new Date());
        orderDetails.setCreatedBy("Admin");
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

    public List<CodeWisePrices> getAllPrices() {

        return itemRepository.getAllPricess(Arrays.asList("12", "13", "15"));
    }
}

