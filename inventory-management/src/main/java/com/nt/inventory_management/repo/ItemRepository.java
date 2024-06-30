package com.nt.inventory_management.repo;

import com.nt.inventory_management.dto.CodeWisePrices;
import com.nt.inventory_management.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select id.price as price, id.item_category as categoryName from item_details id where  id.item_code in (:itemCode)",nativeQuery = true)
    List<CodeWisePrices> getAllPricess(@Param("itemCode") List<String> itemCode);
}
