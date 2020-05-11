package com.cg.dd.app.service;

import java.util.List;

import com.cg.dd.app.entity.RawMaterialStock;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;

public interface RawMaterialStockService {

	public RawMaterialStock addStock(RawMaterialStock stock);

	public String updateStock(int id, int unit, double pricePerUnit, String qualityCheck);

	public Warehouse addWarehouse(Warehouse warehouse);

	public Supplier addSupplier(Supplier supplier);
	
	public List<String> getRawmaterialNames();
	
	public List<Integer> getRawmaterialIds();
}
