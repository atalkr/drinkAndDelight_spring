package com.cg.dd.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.dd.app.entity.Warehouse;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Integer> {

}
