package com.cg.dd.app.dao;

import java.util.List;

import com.cg.dd.app.entity.RawMaterialStock;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;

public interface RawMaterialStockDAO {
	
	public boolean doesRawMaterialStockIdExists(int id);

	public RawMaterialStock addStock(RawMaterialStock stock);

	public void updateStock(int id, int unit, double pricePerUnit, String qualityCheck);

	public Warehouse addWarehouse(Warehouse warehouse);

	public Supplier addSupplier(Supplier supplier);
	
	public List<String> getRawmaterialNames();
	
	public List<Integer> getRawmaterialIds();
}
