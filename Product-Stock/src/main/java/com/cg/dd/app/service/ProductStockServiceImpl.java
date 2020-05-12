package com.cg.dd.app.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductStock;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.repository.DistributorRepo;
import com.cg.dd.app.repository.ProductStockRepo;
import com.cg.dd.app.repository.WarehouseRepo;

/**
 * @author Atal_kumar May 04, 2020
 */
@Service
public class ProductStockServiceImpl implements ProductStockService {

	@Autowired
	ProductStockRepo productRepo;

	@Autowired
	WarehouseRepo warehouseRepo;

	@Autowired
	DistributorRepo distributorRepo;

	@Transactional
	@Override
	public ProductStock addStock(ProductStock stock) {
		stock.setProcessDate(LocalDate.now());
		return productRepo.saveAndFlush(stock);
	}

	@Transactional
	@Override
	public String updateStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		if (productRepo.existsById(id)) {
			ProductStock stock=productRepo.getOne(id);
			stock.setQualityCheck(qualityCheck);
			stock.setPricePerUnit(pricePerUnit);
			stock.setQuantityUnit(unit);
			stock.setPrice(pricePerUnit*unit);
			productRepo.saveAndFlush(stock);
			return "Successfully updated";
		}
		return "updation failed ! Enter valid stockId";
	}

	@Transactional
	@Override
	public Warehouse addWarehouse(Warehouse warehouse) {
		return warehouseRepo.saveAndFlush(warehouse);
	}

	@Transactional
	@Override
	public Distributor addDistributor(Distributor distributor) {
		return distributorRepo.saveAndFlush(distributor);
	}

	@Transactional
	@Override
	public List<Integer> getProductStockIds() {
		return productRepo.getProductIds();
	}

	@Transactional
	@Override
	public List<String> getProductStockNames() {
		return productRepo.getProductNames();
	}
}
