package com.cg.dd.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.dao.RawMaterialStockDAO;
import com.cg.dd.app.entity.RawMaterialStock;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;

@Service
public class RawMaterialStockServiceImpl implements RawMaterialStockService {

	@Autowired
	RawMaterialStockDAO dao;

	@Override
	public RawMaterialStock addStock(RawMaterialStock stock) {
		double totalPrice = stock.getPricePerUnit() * stock.getQuantityUnit();
		stock.setProcessDate(LocalDate.now());
		stock.setPrice(totalPrice);
		return dao.addStock(stock);
	}

	@Override
	public String updateStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		if (dao.doesRawMaterialStockIdExists(id)) {
			dao.updateStock(id, unit, pricePerUnit, qualityCheck);
			return "updated";
		}
		return "updation failed";
	}

	@Override
	public Warehouse addWarehouse(Warehouse warehouse) {
		return dao.addWarehouse(warehouse);
	}

	@Override
	public Supplier addSupplier(Supplier supplier) {
		return dao.addSupplier(supplier);
	}

	@Override
	public List<String> getRawmaterialNames() {
		return dao.getRawmaterialNames();
	}

	@Override
	public List<Integer> getRawmaterialIds() {
		return dao.getRawmaterialIds();
	}
}
