package com.nt.inventory_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Builder
@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    private Date createdDate;
    private String createdBy;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order_item_fk_Id", referencedColumnName = "orderId")
    private List<OrderedItemDetails> orderedItems;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<OrderedItemDetails> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItemDetails> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId=" + orderId +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", orderedItems=" + orderedItems +
                '}';
    }
}
