package com.cg.dd.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.dao.RawmaterialDAO;
import com.cg.dd.app.entity.RawMaterialOrder;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
@Service
public class RawmaterialServiceImpl implements RawmaterialService {

	@Autowired
	RawmaterialDAO dao;

	@Override
	public boolean doesRawMaterialOrderIdExists(int id) {
		return dao.doesRawMaterialOrderIdExists(id);
	}

	@Override
	public RawMaterialOrder saveRawmaterialOrder(RawMaterialOrder order) {
		order.setDateOfOrder(java.time.LocalDate.now());
		double totalPrice = order.getQuantityUnit() * order.getPricePerUnit();
		order.setTotalPrice(totalPrice);
		order.setDeliveryStatus("ordered");
		return dao.saveRawmaterialOrder(order);
	}

	@Override
	public String trackRawmaterialOrder(int id) {
		return dao.trackRawmaterialOrder(id);
	}

	@Override
	public String updateRawmaterialOrder(int id, String status) {
		if (dao.doesRawMaterialOrderIdExists(id)) {
			dao.updateRawmaterialOrder(id, status);
			return "updated";
		}
		return "updation failed";
	}

	@Override
	public List<Supplier> getAllSupplier() {
		return dao.getAllSupplier();
	}

	@Override
	public Supplier getSupplierById(int id) {
		return dao.getSupplierById(id);
	}

	@Override
	public List<Warehouse> getWarehouses() {
		return dao.getWarehouses();
	}

	@Override
	public List<RawMaterialOrder> getAllOrders() {
		return dao.getAllOrders();
	}

}
