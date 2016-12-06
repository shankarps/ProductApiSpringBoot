package com.shankar.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shankar.model.ErrorModel;
import com.shankar.model.Product;
import com.shankar.service.ProductService;

@Component
@Path("/products")
public class ProductController {
	
	private ProductService productService;

	@Autowired 
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
    	
    	List<Product> products = productService.listAllProducts();
    	return Response.status(Response.Status.OK).entity(products).build();
        
    }
	
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByCode(@PathParam("code") String code) {
    	Product product = productService.getProductByCode(code);
    	if(product == null){
    		ErrorModel error = new ErrorModel();
			error.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			error.setMessage("Product not found");
			return Response
	                .status(Response.Status.NOT_FOUND)
	                .entity(error)
	                .type( MediaType.APPLICATION_JSON)
	                .build();
    	}
    	return Response.status(Response.Status.OK).entity(product).build();
        
    }
    
    @GET
    @Path("/tag/{tag}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductByTag(@PathParam("tag") String tag) {
    	
    	List<Product> products = productService.getProductByTag(tag);
    	return Response.status(Response.Status.OK).entity(products).build();
        
    }
    
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveProduct(Product product) {
		if(product == null || product.getName() == null || product.getName().equals("") ){
			//throw new WebApplicationException(Response.Status.BAD_REQUEST);
			ErrorModel error = new ErrorModel();
			error.setCode(Response.Status.BAD_REQUEST.getStatusCode());
			error.setMessage("Invalid Input. Product name is required.");;
			return Response
	                .status(Response.Status.BAD_REQUEST)
	                .entity(error)
	                .type( MediaType.APPLICATION_JSON)
	                .build();
		}
		Product newProduct = productService.saveProduct(product);
		return Response.status(Response.Status.CREATED).entity(newProduct).type( MediaType.APPLICATION_JSON).build();
		
	}
}
