package com.nt.inventory_management.dto;

import com.nt.inventory_management.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDTO {
    private Long orderId;
    private Item  itemDetails;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Item getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(Item itemDetails) {
        this.itemDetails = itemDetails;
    }
}
