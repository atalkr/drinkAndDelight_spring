package com.cg.dd.app.dao;

import java.util.List;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductStock;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
public interface ProductStockDAO {
	
	public boolean doesProductStockIdExists(int id);

	public ProductStock addStock(ProductStock stock);

	public void updateStock(int id, int unit, double pricePerUnit, String qualityCheck);

	public Warehouse addWarehouse(Warehouse warehouse);

	public Distributor addDistributor(Distributor distributor);
	
	public List<Integer> getProductStockIds();
	
	public List<String> getProductStockNames();
}
