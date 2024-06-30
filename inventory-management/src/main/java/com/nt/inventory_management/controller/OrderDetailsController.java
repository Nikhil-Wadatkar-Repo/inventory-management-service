package com.nt.inventory_management.controller;

import com.nt.inventory_management.dto.CodeWisePrices;
import com.nt.inventory_management.dto.OrderDTO;
import com.nt.inventory_management.dto.OrderList;
import com.nt.inventory_management.entity.Item;
import com.nt.inventory_management.entity.OrderDetails;
import com.nt.inventory_management.service.ExcelExportService;
import com.nt.inventory_management.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderDetailsController {

    private final OrderDetailsService service;
    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    public OrderDetailsController(OrderDetailsService OrderDetailsService) {
        this.service = OrderDetailsService;
    }

    @GetMapping
    public List<OrderList> getAllOrderDetails() {
        return service.getAllOrderDetails();
    }
    @GetMapping("/getSampleOrder")
    public OrderDetails createSampleOrder(){
        OrderDetails orderDetails = new OrderDetails();
        List<Item> items = Arrays.asList(Item.builder().build(), Item.builder().build());
        return orderDetails;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long OrderDetailsId) {
        OrderDetails OrderDetails = service.getOrderDetailsById(OrderDetailsId);
        return ResponseEntity.ok().body(OrderDetails);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDTO orderDTO) {
        OrderDetails createdOrderDetails = service.saveOrderDetails(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetails);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long OrderDetailsId) {
        service.deleteOrderDetails(OrderDetailsId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getPrices")
    public  List<CodeWisePrices> getAllPrices() {
        return service.getAllPrices();
    }

}
