package com.cg.dd.app.service;

import java.util.List;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductStock;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
public interface ProductStockService {

	public ProductStock addStock(ProductStock stock);

	public String updateStock(int id, int unit, double pricePerUnit, String qualityCheck);

	public Warehouse addWarehouse(Warehouse warehouse);

	public Distributor addDistributor(Distributor supplier);
	
	public List<Integer> getProductStockIds();
	
	public List<String> getProductStockNames();
}
