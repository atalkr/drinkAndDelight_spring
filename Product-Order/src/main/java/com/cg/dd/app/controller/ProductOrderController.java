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

import com.cg.dd.app.entity.CustomResponse;
import com.cg.dd.app.entity.Distributor;
import com.cg.dd.app.entity.ProductOrder;
import com.cg.dd.app.service.ProductService;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * @author Atal_kumar
 * May 04, 2020
 */
@RestController
@CrossOrigin("http://localhost:4200")
public class ProductOrderController {

	@Autowired
	ProductService service;
    private static final Logger LOGGER=LoggerFactory.getLogger(Application.class);
	ProductOrder order;

	@PostMapping("/addOrder")
	@HystrixCommand(fallbackMethod = "fallbackaddOrder")
	public ProductOrder addOrder(@RequestBody ProductOrder order) {
        LOGGER.info("adding order");
		return service.saveProductOrder(order);
	}

	@GetMapping("/updateOrder/{id}/{status}")
	@HystrixCommand(fallbackMethod = "fallbackupdateOrder")
	public CustomResponse updateOrder(@PathVariable int id, @PathVariable String status) {
		LOGGER.info("updating order");
		return new CustomResponse(200, service.updateProductOrder(id, status));
	}

	@GetMapping("/trackOrder/{id}")
	@HystrixCommand(fallbackMethod = "fallbacktrackOrder")
	public CustomResponse trackOrder(@PathVariable int id) {
		LOGGER.info("tracking order");
		return new CustomResponse(200, service.trackProductOrder(id));
	}

	@GetMapping("/getDistributors")
	@HystrixCommand(fallbackMethod = "fallbackgetDistributor")
	public List<Distributor> getDistributor() {
        LOGGER.info("getting distributor");
		return service.getAllDistributor();
	}

	@GetMapping("/getProductOrders")
	@HystrixCommand(fallbackMethod = "fallbackgetProductOrders")
	public List<ProductOrder> getProductOrders() {
		LOGGER.info("getting orders");
		return service.getProductOrders();
	}

	public ProductOrder fallbackaddOrder(@RequestBody ProductOrder order) {
		LOGGER.error("adding order failed !");
		return new ProductOrder();
	}

	public CustomResponse fallbackupdateOrder(@PathVariable int id, @PathVariable String status) {
		LOGGER.error("updating order failed !");
		return new CustomResponse(400, "Bad request.Operation failed");
	}

	public CustomResponse fallbacktrackOrder(@PathVariable int id) {
		LOGGER.error("tracking order failed !");
		return new CustomResponse(400, "Bad request.Operation failed");
	}

	public List<Distributor> fallbackgetDistributor() {
		LOGGER.error("getting distributor failed !");
		return new ArrayList<>();
	}

	public List<ProductOrder> fallbackgetProductOrders() {
		LOGGER.error("getting product orders failed !");
		return new ArrayList<>();
	}

}
