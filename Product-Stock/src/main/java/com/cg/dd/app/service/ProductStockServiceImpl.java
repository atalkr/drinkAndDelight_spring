package com.cg.dd.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.dao.ProductStockDAO;
import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductStock;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
@Service
public class ProductStockServiceImpl implements ProductStockService {

	@Autowired
	ProductStockDAO dao;

	public ProductStock addStock(ProductStock stock) {
		stock.setProcessDate(LocalDate.now());
		return dao.addStock(stock);
	}

	public String updateStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		if (dao.doesProductStockIdExists(id)) {
			dao.updateStock(id, unit, pricePerUnit, qualityCheck);
			return "Successfully updated";
		}
		return "updation failed ! Enter valid stockId";
	}

	public Warehouse addWarehouse(Warehouse warehouse) {
		return dao.addWarehouse(warehouse);
	}

	public Distributor addDistributor(Distributor distributor) {
		return dao.addDistributor(distributor);
	}

	public List<Integer> getProductStockIds() {
		return dao.getProductStockIds();
	}

	public List<String> getProductStockNames() {
		return dao.getProductStockNames();
	}
}
