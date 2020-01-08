package com.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.exception.ProductDoesNotExistsException;
import com.service.exception.ProductExistsException;
import com.service.model.ErrorModel;
import com.service.model.Product;
import com.service.service.ProductDataService;

@RestController
@RequestMapping("/product")
public class ControllerRest {

	@Autowired
	ProductDataService productDataService;
	
	@GetMapping("/health")
	public ResponseEntity<String> retrieveAllStudents() {
		return new ResponseEntity<>("running", HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ProductExistsException {
		Product savedProduct = productDataService.save(product);
		if(savedProduct == null) {
			ErrorModel em = new ErrorModel(HttpStatus.CONFLICT.value(), "product already exists");
			throw new ProductExistsException(em);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}
	
	@GetMapping("/getByProductName/{productName}")
	public ResponseEntity<Product> getProduct(@PathVariable String productName) throws ProductDoesNotExistsException {
		Product product = productDataService.getByProductName(productName);
		if(product == null) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "product does not exists");
			throw new ProductDoesNotExistsException(em);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() throws ProductDoesNotExistsException {
		List<Product> product = productDataService.getAllProducts();
		if(product.isEmpty()) {
			ErrorModel em = new ErrorModel(HttpStatus.NOT_FOUND.value(), "No product exists");
			throw new ProductDoesNotExistsException(em);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		return productDataService.updateUser(product, id);
	}
	
	@PutMapping("/updateByProductName/{productName}")
	public ResponseEntity<Object> updateProductByName(@RequestBody Product product, @PathVariable String productName) {
		return productDataService.updateUser(product, productName);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
		return productDataService.deleteProduct(id);
	}
	
	@DeleteMapping("/delete/{productName}")
	public ResponseEntity<Object> deleteProduct(@PathVariable String productName) {
		return productDataService.deleteByProductName(productName);
	}
	
}
