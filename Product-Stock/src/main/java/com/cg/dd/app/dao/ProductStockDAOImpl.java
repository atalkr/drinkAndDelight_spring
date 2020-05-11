package com.cg.dd.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductStock;
import com.cg.dd.app.entity.Warehouse;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ProductStockDAOImpl implements ProductStockDAO {

	@Autowired
	EntityManager em;

	@Override
	public boolean doesProductStockIdExists(int id) {
		ProductStock order = em.find(ProductStock.class, id);
		return order == null ? false : true;
	}

	@Override
	public ProductStock addStock(ProductStock stock) {
		em.persist(stock);
		return stock;
	}

	@Override
	public void updateStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		ProductStock stock = em.find(ProductStock.class, id);
		stock.setPricePerUnit(pricePerUnit);
		stock.setQuantityUnit(unit);
		stock.setQualityCheck(qualityCheck);
		double totalPrice = stock.getQuantityUnit() * stock.getPricePerUnit();
		stock.setPrice(totalPrice);
		em.merge(stock);
	}

	@Override
	public Warehouse addWarehouse(Warehouse warehouse) {
		em.persist(warehouse);
		return warehouse;
	}

	@Override
	public Distributor addDistributor(Distributor distributor) {
		em.persist(distributor);
		return distributor;
	}

	@Override
	public List<Integer> getProductStockIds() {
		return em.createQuery("SELECT s.stockId FROM ProductStock s").getResultList();
	}

	@Override
	public List<String> getProductStockNames() {
		return em.createQuery("SELECT s.name FROM ProductStock s").getResultList();
	}
}
