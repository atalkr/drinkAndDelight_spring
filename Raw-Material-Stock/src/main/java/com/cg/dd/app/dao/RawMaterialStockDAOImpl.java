package com.cg.dd.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dd.app.entity.RawMaterialStock;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class RawMaterialStockDAOImpl implements RawMaterialStockDAO {

	@Autowired
	EntityManager em;

	@Override
	public boolean doesRawMaterialStockIdExists(int id) {
		RawMaterialStock order = em.find(RawMaterialStock.class, id);
		return order == null ? false : true;
	}

	@Override
	public RawMaterialStock addStock(RawMaterialStock stock) {
		em.persist(stock);
		return stock;
	}

	@Override
	public void updateStock(int id, int unit, double pricePerUnit, String qualityCheck) {
		RawMaterialStock stock = em.find(RawMaterialStock.class, id);
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
	public Supplier addSupplier(Supplier supplier) {
		em.persist(supplier);
		return supplier;
	}

	@Override
	public List<String> getRawmaterialNames() {
		return em.createQuery("select s.name FROM RawMaterialStock s").getResultList();
	}

	@Override
	public List<Integer> getRawmaterialIds() {
		return em.createQuery("select s.stockId FROM RawMaterialStock s").getResultList();
	}

}
