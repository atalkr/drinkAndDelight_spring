package com.cg.dd.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dd.app.entity.RawMaterialOrder;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.service.RawmaterialService;
import com.cg.dd.app.util.CustomResponse;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * @author Atal_kumar
 * May 05, 2020
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class RawmaterialOrderController {

	@Autowired
	RawmaterialService service;
    private static final Logger LOGGER=LoggerFactory.getLogger(Application.class);
	RawMaterialOrder order;

	@PostMapping("/addOrder")
	@HystrixCommand(fallbackMethod = "fallbackaddOrder")
	public RawMaterialOrder addOrder(@RequestBody RawMaterialOrder order) {
		LOGGER.info("adding order");
		return service.saveRawmaterialOrder(order);
	}

	@GetMapping("/updateOrder/{id}/{status}")
	@HystrixCommand(fallbackMethod = "fallbackupdateOrder")
	public CustomResponse updateOrder(@PathVariable int id, @PathVariable String status) {
		LOGGER.info("updating order");
		return new CustomResponse(200, service.updateRawmaterialOrder(id, status));
	}

	@GetMapping("/trackOrder/{id}")
	@HystrixCommand(fallbackMethod = "fallbacktrackOrder")
	public CustomResponse trackOrder(@PathVariable int id) {
		LOGGER.info("tracking order");
		return new CustomResponse(200, service.trackRawmaterialOrder(id));
	}

	@GetMapping("/supplier/{id}")
	@HystrixCommand(fallbackMethod = "fallbackgetSupplier")
	public Supplier getSupplier(@PathVariable int id) {
		LOGGER.info("getting supplier by id");
		return service.getSupplierById(id);
	}

	@GetMapping("/getWarehouses")
	@HystrixCommand(fallbackMethod = "fallbackgetWarehouses")
	public List<Warehouse> getWarehouses() {
		LOGGER.info("getting warehouses");
		return service.getWarehouses();
	}

	@GetMapping("/getSuppliers")
	@HystrixCommand(fallbackMethod = "fallbackgetSuppliers")
	public List<Supplier> getSuppliers() {
		LOGGER.info("getting supplier");
		return service.getAllSupplier();
	}

	@GetMapping("/getRawmaterialOrders")
	@HystrixCommand(fallbackMethod = "fallbackgetAllOrders")
	public List<RawMaterialOrder> getAllOrders() {
		LOGGER.info("getting rawmaterial orders");
		return service.getAllOrders();
	}

	public RawMaterialOrder fallbackaddOrder(@RequestBody RawMaterialOrder order) {
		LOGGER.error("adding order failed !");
		return new RawMaterialOrder();
	}

	public CustomResponse fallbackupdateOrder(@PathVariable int id, @PathVariable String status) {
		LOGGER.error("updating order failed !");
		return new CustomResponse(555, "operation failed");
	}

	public CustomResponse fallbacktrackOrder(@PathVariable int id) {
		LOGGER.error("tracking order failed !");
		return new CustomResponse(555, "operation failed");
	}

	public Supplier fallbackgetSupplier(@PathVariable int id) {
		LOGGER.error("getting supplier by id failed !");
		return new Supplier();
	}

	public List<Warehouse> fallbackgetWarehouses() {
		LOGGER.error("getting warehouses failed !");
		return new ArrayList<>();
	}

	public List<Supplier> fallbackgetSuppliers() {
		LOGGER.error("getting suppliers failed !");
		return new ArrayList<>();
	}

	public List<RawMaterialOrder> fallbackgetAllOrders() {
		LOGGER.error("getting all orders failed !");
		return new ArrayList<>();
	}
}
