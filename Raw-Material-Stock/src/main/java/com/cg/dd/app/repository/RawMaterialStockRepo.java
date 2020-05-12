package com.cg.dd.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dd.app.entity.RawMaterialStock;

@Repository
public interface RawMaterialStockRepo extends JpaRepository<RawMaterialStock, Integer> {
	
    @Query("SELECT r.name FROM RawMaterialStock r")
    List<String> getRawMaterialNames();
    
    @Query("SELECT r.stockId FROM RawMaterialStock r")
    List<Integer> getRawMaterialIds();
    

}
