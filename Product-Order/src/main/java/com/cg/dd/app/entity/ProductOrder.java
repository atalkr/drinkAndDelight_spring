package com.cg.dd.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author Atal_kumar
 * May 04, 2020
 */
@Entity
@Table(name = "tbl_product_orders")
@SequenceGenerator(name="seq", initialValue=1001, allocationSize=100)
public class ProductOrder {

	@Id
	@Column(name = "pk_order_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	int orderId;

	@Column(name = "Name")
	@NotNull
	String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_distributor_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotNull
	Distributor distributor;

	@Column(name = "qunatity_unit")
	@NotNull
	int quantityUnit;

	@Column(name = "date_of_order")
	@NotNull
	LocalDate dateOfOrder;

	@Column(name = "date_of_delivery")
	@NotNull
	LocalDate dateOfDelivery;

	@Column(name = "price_per_unit")
	@NotNull
	double pricePerUnit;

	@Column(name = "total_price")
	@NotNull
	double totalPrice;

	@Column(name = "delivery_status")
	@NotNull
	String deliveryStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_warehouse_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotNull
	Warehouse warehouse;

	public ProductOrder() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(int quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public LocalDate getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(LocalDate dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	
}
