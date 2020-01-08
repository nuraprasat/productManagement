package com.service.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.model.Category;
import com.service.model.Merchant;
import com.service.model.Product;
import com.service.service.ProductDataService;

@RunWith(MockitoJUnitRunner.class)
public class ControllerRestTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;
	
	@Mock
	private ProductDataService productService;
	
	@InjectMocks
	ControllerRest controllerRest;
	
	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerRest).build();
	}

	@Test
	public void AllProductShouldBeReturnedFromService() throws Exception {
		Mockito.when(productService.getAllProducts()).thenReturn(getListOfProduct());
		MvcResult result = this.mockMvc.perform(get("/product/getAllProduct").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		List<Product> proList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Product>>() {});
		assertNotNull(proList.get(0));
		assertEquals(getListOfProduct().get(0), proList.get(0));
	}
	
	@Test(expected=NestedServletException.class)
	public void AllProductReturnEmpty() throws Exception {
		List<Product> prodList = new ArrayList<>();
		Mockito.when(productService.getAllProducts()).thenReturn(prodList);
		this.mockMvc.perform(get("/product/getAllProduct").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(404))
														.andReturn();
	}
	
	@Test
	public void productShouldBeReturnedbasedOnProductName() throws Exception {
		Mockito.when(productService.getByProductName(Mockito.anyString())).thenReturn(getListOfProduct().get(0));
		MvcResult result = this.mockMvc.perform(get("/product/getByProductName/{productName}",1).param("productName", "sample").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Product proList = mapper.readValue(result.getResponse().getContentAsString(), Product.class);
		assertNotNull(proList);
		assertEquals(getListOfProduct().get(0), proList);
	}
	
	@Test
	public void productShouldBeReturnedbasedOnId() throws Exception {
		ResponseEntity<Object> res = new ResponseEntity<Object>(HttpStatus.OK);
		Mockito.when(productService.deleteProduct(Mockito.anyInt())).thenReturn(res);
		this.mockMvc.perform(delete("/product/delete/{id}",1).param("id", "1").accept(MediaType.APPLICATION_JSON_VALUE))
														.andExpect(status().is(200))
														.andReturn();
	}
	
	private List<Product> getListOfProduct() {
		List<Product> productList = new ArrayList<>();
		Product product = new Product();
		product.setCategory(getCategory());
		product.setMerchant(getMerchant());
		product.setDescription("short desc");
		product.setImage("/img/");
		product.setMsrp(0.0);
		product.setPrice(10.0);
		product.setProductAvailability(true);
		product.setProductName("sample");
		product.setUrl("/product/");
		product.setProductId(1);
		productList.add(product);
		return productList;
	}

	private Merchant getMerchant() {
		Merchant merchant = new Merchant();
		merchant.setMerchantId(0);
		merchant.setMerchantName("nike");
		merchant.setDescription("merchant desc");
		return merchant;
	}
	
	private Category getCategory() {
		Category category = new Category();
		category.setCategoryId(0);
		category.setCategoryName("shoes");
		category.setSubcategoryName("nike air");
		category.setGender("men");
		return category;
	}
}
