package com.cg.dd.app.dao;

import java.util.List;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductOrder;
/**
 * @author Atal_kumar
 * May 04, 2020
 */

public interface ProductDAO {

	public boolean doesProductOrderIdExists(int id);

	public ProductOrder saveProductOrder(ProductOrder order);

	public String trackProductOrder(int id);

	public void updateProductOrder(int id, String status);

	public List<Distributor> getAllDistributor();

	public Distributor getDistributorById(int id);
	
	public List<ProductOrder> getProductOrders();

}
