package com.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.model.Category;
import com.service.model.Merchant;
import com.service.model.Product;
import com.service.repository.CategoryRepository;
import com.service.repository.MerchantRepository;
import com.service.repository.ProductRepository;

@Service
public class ProductDataService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	MerchantRepository merchantRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Product getByProductName(String productName) {
		Optional<Product> optProduct = productRepository.findByProductName(productName);
		if(optProduct.isPresent())
			return optProduct.get();
		return null;
	}

	public ResponseEntity<Object> updateUser(Product product, Integer id) {
		Optional<Product> userOptional = productRepository.findById(id);
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		product.setProductId(userOptional.get().getProductId());
		productRepository.save(product);
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Object> updateUser(Product product, String productName) {
		Optional<Product> userOptional = productRepository.findByProductName(productName);
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		product.setProductId(userOptional.get().getProductId());
		productRepository.save(product);
		
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Object> deleteProduct(Integer id) {
		Optional<Product> userOptional = productRepository.findById(id);
		int categoryCount = 0;
		int merchantCount = 0;
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		
		Category category = userOptional.get().getCategory();
		Merchant merchant = userOptional.get().getMerchant();
		if(category != null)
			categoryCount = productRepository.getCategoryCount(category.getCategoryId());
		if(merchant != null)
			merchantCount = productRepository.getMerchantCount(merchant.getMerchantId());
		productRepository.deleteProduct(id);
		if(categoryCount == 1) {
			categoryRepository.deleteCategory(userOptional.get().getCategory().getCategoryId());
		}
		if(merchantCount == 1) {
			merchantRepository.deleteMerchant(userOptional.get().getMerchant().getMerchantId());
		}
		
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Object> deleteByProductName(String productName) {
		Optional<Product> userOptional = productRepository.findByProductName(productName);
		int categoryCount = 0;
		int merchantCount = 0;
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		Category category = userOptional.get().getCategory();
		Merchant merchant = userOptional.get().getMerchant();
		if(category != null)
			categoryCount = productRepository.getCategoryCount(category.getCategoryId());
		if(merchant != null)
			merchantCount = productRepository.getMerchantCount(merchant.getMerchantId());
		productRepository.deleteProduct(userOptional.get().getProductId());
		if(categoryCount == 1) {
			categoryRepository.deleteCategory(userOptional.get().getCategory().getCategoryId());
		}
		if(merchantCount == 1) {
			merchantRepository.deleteMerchant(userOptional.get().getMerchant().getMerchantId());
		}
		
		return ResponseEntity.ok().build();
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
