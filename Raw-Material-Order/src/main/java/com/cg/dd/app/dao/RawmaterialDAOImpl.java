package com.cg.dd.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dd.app.entity.RawMaterialOrder;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
@Repository
@SuppressWarnings("unchecked")
public class RawmaterialDAOImpl implements RawmaterialDAO {

	@Autowired
	EntityManager em;

	@Transactional
	@Override
	public boolean doesRawMaterialOrderIdExists(int id) {
		RawMaterialOrder order = em.find(RawMaterialOrder.class, id);
		return order == null ? false : true;
	}

	@Transactional
	@Override
	public RawMaterialOrder saveRawmaterialOrder(RawMaterialOrder order) {
		Supplier s = order.getSupplier();
		order.setSupplier(s);
		em.persist(order);
		return order;
	}

	@Transactional
	@Override
	public String trackRawmaterialOrder(int id) {
		RawMaterialOrder order = em.find(RawMaterialOrder.class, id);
		return order.getDeliveryStatus();
	}

	@Transactional
	@Override
	public void updateRawmaterialOrder(int id, String status) {
		RawMaterialOrder order = em.find(RawMaterialOrder.class, id);
		order.setDeliveryStatus(status);
		em.merge(order);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		return em.createQuery("select s FROM Supplier s").getResultList();
	}

	@Override
	public Supplier getSupplierById(int id) {
		return em.find(Supplier.class, id);
	}

	@Override
	public List<Warehouse> getWarehouses() {
		return em.createQuery("select w FROM Warehouse w").getResultList();
	}

	@Override
	public List<RawMaterialOrder> getAllOrders() {
		return em.createQuery("select o FROM RawMaterialOrder o").getResultList();
	}

}
