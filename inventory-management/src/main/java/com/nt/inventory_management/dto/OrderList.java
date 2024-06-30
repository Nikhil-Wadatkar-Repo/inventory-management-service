package com.nt.inventory_management.dto;

import java.util.Date;

public interface OrderList {
    Long getOrderId();

    Long getFinalPrice();

    Date getCreatedDate();

    String getCreatedBy();

    String getCustomerName();

    String getWorkerName();
}
