package com.service.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.service.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductDataServiceTest {

	@InjectMocks
	ProductDataService productDataService;
	
	@Mock
	ProductRepository productRepository;
	
}
