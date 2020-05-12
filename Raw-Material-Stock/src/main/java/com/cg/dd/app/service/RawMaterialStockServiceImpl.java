package com.cg.dd.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.entity.RawMaterialStock;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.repository.RawMaterialStockRepo;
import com.cg.dd.app.repository.SupplierRepo;
import com.cg.dd.app.repository.WarehouseRepo;

@Service
public class RawMaterialStockServiceImpl implements RawMaterialStockService {

	@Autowired
	RawMaterialStockRepo rawRepo;
	
	@Autowired
	SupplierRepo supRepo;

	@Autowired
	WarehouseRepo warRepo;
	
	@Override
	@Transactional
	public RawMaterialStock addStock(RawMaterialStock stock) {
		double totalPrice = stock.getPricePerUnit() * stock.getQuantityUnit();
		stock.setProcessDate(LocalDate.now());
		stock.setPrice(totalPrice);
		return rawRepo.saveAndFlush(stock);
	}

	@Override
	@Transactional
	public String updateStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		if (rawRepo.existsById(id)) {
			RawMaterialStock stock=rawRepo.getOne(id);
			stock.setQualityCheck(qualityCheck);
			stock.setQuantityUnit(unit);
			stock.setPricePerUnit(pricePerUnit);
			stock.setPrice(unit*pricePerUnit);
			rawRepo.saveAndFlush(stock);
			return "updated";
		}
		return "updation failed";
	}

	@Override
	@Transactional
	public Warehouse addWarehouse(Warehouse warehouse) {
		return warRepo.saveAndFlush(warehouse);
	}

	@Override
	@Transactional
	public Supplier addSupplier(Supplier supplier) {
		return supRepo.saveAndFlush(supplier);
	}

	
	@Override
	@Transactional
	public List<String> getRawmaterialNames() {
		return rawRepo.getRawMaterialNames();
	}

	@Override
	@Transactional
	public List<Integer> getRawmaterialIds() {
		return rawRepo.getRawMaterialIds();
	}
}
