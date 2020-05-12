package com.cg.dd.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dd.app.entity.ProductStock;

@Repository
public interface ProductStockRepo extends JpaRepository<ProductStock,Integer> {

    @Query("SELECT p.name FROM ProductStock p")
    List<String> getProductNames();
    
    @Query("SELECT p.stockId FROM ProductStock p")
    List<Integer> getProductIds();
    
}

