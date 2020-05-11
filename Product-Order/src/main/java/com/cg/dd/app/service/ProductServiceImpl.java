package com.cg.dd.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dd.app.dao.ProductDAO;
import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductOrder;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO dao;

	@Override
	public boolean doesProductOrderIdExists(int id) {
		return dao.doesProductOrderIdExists(id);
	}

	@Override
	public ProductOrder saveProductOrder(ProductOrder order) {
		order.setDateOfOrder(java.time.LocalDate.now());
		double totalPrice = order.getQuantityUnit() * order.getPricePerUnit();
		order.setTotalPrice(totalPrice);
		order.setDeliveryStatus("ordered");
		return dao.saveProductOrder(order);
	}

	@Override
	public String trackProductOrder(int id) {
		return dao.trackProductOrder(id);
	}

	@Override
	public String updateProductOrder(int id, String status) {
		if (dao.doesProductOrderIdExists(id)) {
			dao.updateProductOrder(id, status);
			return "updated";
		}
		return "updation failed";
	}

	@Override
	public List<Distributor> getAllDistributor() {
		return dao.getAllDistributor();
	}

	@Override
	public Distributor getDistributorById(int id) {
		return dao.getDistributorById(id);
	}

	@Override
	public List<ProductOrder> getProductOrders() {
		return dao.getProductOrders();
	}

}
