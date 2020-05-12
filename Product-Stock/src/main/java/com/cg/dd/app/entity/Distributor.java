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
/**
 * @author Atal_kumar
 * May 04, 2020
 */
@Entity
@Table(name = "distributors")
@SequenceGenerator(name = "seq", initialValue = 1001, allocationSize = 100)
public class Distributor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "pk_distributor_id")
	int distributorId;

	@Column(name = "name")
	@NotNull
	String name;

	@Column(name = "location")
	@Size(min = 5, max = 15)
	String location;

	@Column(name = "phone_number")
	long phoneNumber;

	public Distributor() {
	}

	public int getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
