package com.cg.dd.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.entity.RawMaterialOrder;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.repository.RawMaterialOrderRepo;
import com.cg.dd.app.repository.SupplierRepo;
import com.cg.dd.app.repository.WarehouseRepo;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
@Service
public class RawmaterialServiceImpl implements RawmaterialService {

	@Autowired
	RawMaterialOrderRepo orderRepo;
	
	@Autowired
	SupplierRepo supplierRepo;
	
	@Autowired
	WarehouseRepo warehouseRepo;

	@Override
	public boolean doesRawMaterialOrderIdExists(int id) {
		return orderRepo.existsById(id);
	}

	@Override
	@Transactional
	public RawMaterialOrder saveRawmaterialOrder(RawMaterialOrder order) {
		order.setDateOfOrder(java.time.LocalDate.now());
		double totalPrice = order.getQuantityUnit() * order.getPricePerUnit();
		order.setTotalPrice(totalPrice);
		order.setDeliveryStatus("ordered");
		return orderRepo.saveAndFlush(order);
	}

	@Override
	@Transactional
	public String trackRawmaterialOrder(int id) {
		if (orderRepo.existsById(id)) {
			RawMaterialOrder order=orderRepo.getOne(id);
			return order.getDeliveryStatus();
		}
		return "invalid order id";
	}

	@Override
	@Transactional
	public String updateRawmaterialOrder(int id, String status) {
		if (orderRepo.existsById(id)) {
			orderRepo.updateStatus(id, status);
			return "updated";
		}
		return "updation failed";
	}

	@Override
	@Transactional
	public List<Supplier> getAllSupplier() {
		return supplierRepo.findAll();
	}

	@Override
	@Transactional
	public Supplier getSupplierById(int id) {
		if (orderRepo.existsById(id)) {
			return supplierRepo.findById(id).orElse(null);
		}
		return new Supplier();
		
	}

	@Override
	@Transactional
	public List<Warehouse> getWarehouses() {
		return warehouseRepo.findAll();
	}

	@Override
	@Transactional
	public List<RawMaterialOrder> getAllOrders() {
		return orderRepo.findAll();
	}

}
