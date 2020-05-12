package com.cg.dd.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.dd.app.entity.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

}
