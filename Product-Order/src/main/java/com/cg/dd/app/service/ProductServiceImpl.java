package com.cg.dd.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductOrder;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.repository.DistributorRepo;
import com.cg.dd.app.repository.ProductRepo;
import com.cg.dd.app.repository.WarehouseRepo;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	DistributorRepo disRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	WarehouseRepo warRepo;

	@Override
	public boolean doesProductOrderIdExists(int id) {
		return productRepo.existsById(id);
	}

	@Override
	@Transactional
	public ProductOrder saveProductOrder(ProductOrder order) {
		order.setDateOfOrder(java.time.LocalDate.now());
		double totalPrice = order.getQuantityUnit() * order.getPricePerUnit();
		order.setTotalPrice(totalPrice);
		order.setDeliveryStatus("ordered");
		return productRepo.saveAndFlush(order);
	}

	@Override
	@Transactional
	public String trackProductOrder(int id) {
		if (productRepo.existsById(id)) {
			ProductOrder order=productRepo.getOne(id);
			return order.getDeliveryStatus();
		}
		return "invalid id";
	}

	@Override
	@Transactional
	public String updateProductOrder(int id, String status) {
		if (productRepo.existsById(id)) {
			productRepo.updateStatus(id, status);
			
			return "updated";
		}
		
		return "updation failed";
	}

	@Override
	@Transactional
	public List<Distributor> getAllDistributor() {
		return disRepo.findAll();
	}
	@Override
	@Transactional
	public List<Warehouse> getAllWarehouse() {
		return warRepo.findAll();
	}

	@Override
	@Transactional
	public Distributor getDistributorById(int id) {
		return disRepo.getOne(id);
	}

	@Override
	@Transactional
	public List<ProductOrder> getProductOrders() {
		return productRepo.findAll();
	}

}
