package com.nt.inventory_management.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class OrderDTO {
    private String customerName;
    private String workerName;
    private List<ItemListDTO> orderedItems;

}
