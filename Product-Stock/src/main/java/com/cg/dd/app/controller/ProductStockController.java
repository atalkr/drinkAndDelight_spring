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

import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductStock;
import com.cg.dd.app.entity.Warehouse;
import com.cg.dd.app.service.ProductStockService;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Atal_kumar May 04, 2020
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class ProductStockController {

	@Autowired
	ProductStockService service;
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@PostMapping("/addStock")
	@HystrixCommand(fallbackMethod = "fallbackaddStock")
	public ProductStock addStock(@RequestBody ProductStock stock) {
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

	@PostMapping("/addDistributor")
	@HystrixCommand(fallbackMethod = "fallbackaddDistributor")
	public Distributor addDistributor(@RequestBody Distributor distributor) {
		LOGGER.info("adding distributor");
		return service.addDistributor(distributor);
	}

	@GetMapping("/getProductIds")
	@HystrixCommand(fallbackMethod = "fallbackgetProductStockIds")
	public List<Integer> getProductStockIds() {
		LOGGER.info("getting product stock ids");
		return service.getProductStockIds();
	}

	@GetMapping("/getProductNames")
	@HystrixCommand(fallbackMethod = "fallbackgetProductStockNames")
	public List<String> getProductStockNames() {
		LOGGER.info("getting product stock names");
		return service.getProductStockNames();
	}

	public ProductStock fallbackaddStock(@RequestBody ProductStock stock) {
		LOGGER.error("adding stock failed !");
		return new ProductStock();
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

	public Distributor fallbackaddDistributor(@RequestBody Distributor distributor) {
		LOGGER.error("adding distributor failed !");
		return new Distributor();
	}

	public List<Integer> fallbackgetProductStockIds() {
		LOGGER.error("getting stock ids failed !");
		return new ArrayList<>();
	}

	public List<String> fallbackgetProductStockNames() {
		LOGGER.error("getting stock names failed !");
		return new ArrayList<>();
	}
}
