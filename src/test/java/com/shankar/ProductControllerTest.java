package com.shankar;

import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.shankar.controllers.ProductController;
import com.shankar.model.Product;
import com.shankar.service.ProductService;

/*
 * This Test class is for testing the Controller.
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

	@Autowired
	private ProductController productController;

	@MockBean
	private ProductService productService;

	private Product testProduct;

	@Before
	public void initPorduct() {
		testProduct = new Product();
		testProduct.setCode("1");
		testProduct.setPrice(120.00d);
		testProduct.setName("Test 1");
		Set<String> tags = new HashSet<String>();
		tags.add("computer");
		testProduct.setTags(tags);
	}

	@Test
	public void testSaveProduct() throws Exception {
		// Create new Product
		Product product = new Product();
		product.setName("Test 1");
		product.setPrice(123.21d);
		Set<String> tags = new HashSet<String>();
		tags.add("hardware");
		product.setTags(tags);

		Response response = productController.saveProduct(product);
		assertTrue(response.getStatusInfo() == Response.Status.CREATED);
	}

	@Test
	public void testSaveProductValidation() throws Exception {

		// new Product without name
		Product product = new Product();
		product.setPrice(123.21d);
		Set<String> tags = new HashSet<String>();
		tags.add("hardware");
		product.setTags(tags);

		Response response = productController.saveProduct(product);
		assertTrue(response.getStatusInfo() == Response.Status.BAD_REQUEST);

	}

	@Test
	public void testGetProductByCode() throws Exception {

		given(this.productService.getProductByCode("1"))
				.willReturn(testProduct);

		Response response = productController.getProductByCode("1");
		assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());

	}

	@Test
	public void testGetProductByInvalidCode() throws Exception {
		given(this.productService.getProductByCode("1"))
				.willReturn(testProduct);

		Response response = productController.getProductByCode("INVALID");
		assertTrue(response.getStatus() == Response.Status.NOT_FOUND
				.getStatusCode());

	}

	@Test
	public void testGetProductByTag() throws Exception {
		given(this.productService.getProductByCode("computer")).willReturn(
				testProduct);

		Response response = productController.getProductByTag("computer");
		assertTrue(response.getStatus() == Response.Status.OK
				.getStatusCode());

	}

	@Test
	public void testGetAllProducts() throws Exception {

		Response response = productController.getAllProducts();
		assertTrue(response.getStatus() == Response.Status.OK
				.getStatusCode());

	}

}
