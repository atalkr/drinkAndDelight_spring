package com.cg.dd.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.dd.app.entity.RawMaterialOrder;

@Repository
public interface RawMaterialOrderRepo extends JpaRepository<RawMaterialOrder, Integer> {
	@Modifying
    @Query("UPDATE RawMaterialOrder o SET o.deliveryStatus = :status WHERE o.orderId = :orderId")
    int updateStatus(@Param("orderId") int orderId, @Param("status") String status);
}
