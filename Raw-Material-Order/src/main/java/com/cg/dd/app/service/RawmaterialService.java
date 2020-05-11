package com.cg.dd.app.service;

import java.util.List;

import com.cg.dd.app.entity.RawMaterialOrder;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
public interface RawmaterialService {

	public boolean doesRawMaterialOrderIdExists(int id);

	public RawMaterialOrder saveRawmaterialOrder(RawMaterialOrder order);

	public String trackRawmaterialOrder(int id);

	public String updateRawmaterialOrder(int id, String status);

	public List<Supplier> getAllSupplier();

	public Supplier getSupplierById(int id);
	
	public List<Warehouse> getWarehouses();
	
	public List<RawMaterialOrder> getAllOrders();

}
