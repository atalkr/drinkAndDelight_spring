package com.cg.dd.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductOrder;
/**
 * @author Atal_kumar
 * May 04, 2020
 */

@Repository
@SuppressWarnings("unchecked")
@Transactional
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	EntityManager em;

	@Override
	public boolean doesProductOrderIdExists(int id) {
		ProductOrder order = em.find(ProductOrder.class, id);
		return order == null ? false : true;
	}

	@Override
	public ProductOrder saveProductOrder(ProductOrder order) {
		em.persist(order);
		return order;
	}

	@Override
	public String trackProductOrder(int id) {
		ProductOrder order = em.find(ProductOrder.class, id);
		return order.getDeliveryStatus();
	}

	@Override
	public void updateProductOrder(int id, String status) {
		ProductOrder order = em.find(ProductOrder.class, id);
		order.setDeliveryStatus(status);
		em.merge(order);
	}

	@Override
	public List<Distributor> getAllDistributor() {
		return em.createQuery("select o FROM Distributor o").getResultList();
	}

	@Override
	public Distributor getDistributorById(int id) {
		return em.find(Distributor.class, id);
	}

	@Override
	public List<ProductOrder> getProductOrders() {
		return em.createQuery("select o FROM ProductOrder o").getResultList();
	}

}
