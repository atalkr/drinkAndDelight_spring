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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dd.app.entity.RawMaterialStock;
import com.cg.dd.app.entity.Supplier;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.service.RawMaterialStockService;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin("http://localhost:4200")
public class RawMaterialStockController {

	@Autowired
	RawMaterialStockService service;
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@PostMapping("/addStock")
	@HystrixCommand(fallbackMethod = "fallbackaddStock")
	public RawMaterialStock addStock(@RequestBody RawMaterialStock stock) {
		LOGGER.info("adding stock");
		return service.addStock(stock);
	}

	@GetMapping("/updateStock/{id}/{unit}/{price}/{quality}")
	@HystrixCommand(fallbackMethod = "fallbackupdateStock")
	public String updateStock(@PathVariable int id, @PathVariable int unit, @PathVariable double price,
			@PathVariable String quality) {
		LOGGER.info("updating stock");
		return service.updateStock(id, unit, price, quality);
	}

	@PostMapping("/addWarehouse")
	@HystrixCommand(fallbackMethod = "fallbackaddWarehouse")
	public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
		LOGGER.info("getting warehouses");
		return service.addWarehouse(warehouse);
	}

	@PostMapping("/addSupplier")
	@HystrixCommand(fallbackMethod = "fallbackaddSupplier")
	public Supplier addSupplier(@RequestBody Supplier supplier) {
		LOGGER.info("adding supplier");
		return service.addSupplier(supplier);
	}

	@GetMapping("/getRawMaterialNames")
	@HystrixCommand(fallbackMethod = "fallbackgetRawmaterialNames")
	public List<String> getRawmaterialNames() {
		LOGGER.info("getting rawmaterials names");
		return service.getRawmaterialNames();
	}

	@GetMapping("/getRawMaterialIds")
	@HystrixCommand(fallbackMethod = "fallbackgetRawMaterialIds")
	public List<Integer> getRawMaterialIds() {
		LOGGER.info("getting rawmaterials ids");
		return service.getRawmaterialIds();
	}

	public RawMaterialStock fallbackaddStock(@RequestBody RawMaterialStock stock) {
		LOGGER.error("adding stock failed !");
		return new RawMaterialStock();
	}

	public String fallbackupdateStock(@PathVariable int id, @PathVariable int unit, @PathVariable double price,
			@PathVariable String quality) {
		LOGGER.error("updating stock failed !");
		return "operation failed";
	}

	public Warehouse fallbackaddWarehouse(@RequestBody Warehouse warehouse) {
		LOGGER.error("adding warehouses failed !");
		return new Warehouse();
	}

	public Supplier fallbackaddSupplier(@RequestBody Supplier supplier) {
		LOGGER.error("adding supplier failed !");
		return new Supplier();
	}

	public List<Integer> fallbackgetRawMaterialIds() {
		LOGGER.error("get rawmaterials ids failed !");
		return new ArrayList<>();
	}

	public List<String> fallbackgetRawmaterialNames() {
		LOGGER.error("get rawmaterials names failed !");
		return new ArrayList<>();
	}

}
