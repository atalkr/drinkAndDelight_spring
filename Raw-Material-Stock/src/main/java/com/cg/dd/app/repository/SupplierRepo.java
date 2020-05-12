package com.cg.dd.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.dd.app.entity.Supplier;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
