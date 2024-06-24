package com.nt.inventory_management.repo;

import com.nt.inventory_management.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
