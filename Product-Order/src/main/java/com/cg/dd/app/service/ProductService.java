package com.cg.dd.app.service;

import java.util.List;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductOrder;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
public interface ProductService {

	public boolean doesProductOrderIdExists(int id);

	public ProductOrder saveProductOrder(ProductOrder order);

	public String trackProductOrder(int id);

	public String updateProductOrder(int id, String status);

	public List<Distributor> getAllDistributor();

	public Distributor getDistributorById(int id);
	public List<ProductOrder> getProductOrders();

}
