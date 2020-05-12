package com.cg.dd.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="warehouses")
@SequenceGenerator(name="seq", initialValue=1001, allocationSize=100)
public class Warehouse {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Column(name = "pk_warehouse_id")
	int warehouseId;
	
	@Column(name="location")
	@Size(min=2,max=15)
	@NotNull
	String location;

	public Warehouse() {
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
