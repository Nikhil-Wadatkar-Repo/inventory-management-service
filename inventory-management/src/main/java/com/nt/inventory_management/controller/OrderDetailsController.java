package com.nt.inventory_management.controller;

import com.nt.inventory_management.dto.ItemDTO;
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
    public List<OrderDetails> getAllOrderDetails() {
        return service.getAllOrderDetails();
    }
    @GetMapping("/getSampleOrder")
    public OrderDetails createSampleOrder(){

        OrderDetails orderDetails = new OrderDetails();
        List<Item> items = Arrays.asList(Item.builder().build(), Item.builder().build());
//        orderDetails.setOrderedItems(items);
        return orderDetails;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable Long OrderDetailsId) {
        OrderDetails OrderDetails = service.getOrderDetailsById(OrderDetailsId);
        return ResponseEntity.ok().body(OrderDetails);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDetails> createOrderDetails(@RequestBody OrderDetails OrderDetails) {
        OrderDetails createdOrderDetails = service.saveOrderDetails(OrderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderDetails);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable Long OrderDetailsId) {
        service.deleteOrderDetails(OrderDetailsId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportToExcel() throws IOException {
//        List<OrderDetails> OrderDetailss =new LinkedList<>();
//
//        ByteArrayOutputStream outputStream = excelExportService.exportItemsToExcel();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=OrderDetailss.xlsx");
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(outputStream.toByteArray());
//    }

    @PostMapping("/addItemToOrder")
    public OrderDetails addItemToExistedOrder(ItemDTO itemDTO) {
        return service.addItemToExistedOrder(itemDTO);
    }

    @PostMapping("/deleteItemToOrder/{orderId}/{itemId}")
    public String deleteItemToExistedOrder(@PathVariable("orderId") Long orderId, @PathVariable("itemId") Long itemId) {
        return service.deleteItemToExistedOrder(itemId, orderId);
    }
}
