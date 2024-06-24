package com.nt.inventory_management.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class OrderDTO {
    private Long finalPrice;
    private String customerName;
    private String workerName;
    private List<ItemList> itemLists;

}
