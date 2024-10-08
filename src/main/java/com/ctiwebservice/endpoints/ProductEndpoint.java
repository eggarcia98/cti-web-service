	package com.ctiwebservice.endpoints;
	
	import com.ctiwebservice.xml.school.GetProductsRequest;
	import com.ctiwebservice.xml.school.GetProductsResponse;
	import com.ctiwebservice.xml.school.Product;
	import com.ctiwebservice.service.ProductService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.ws.server.endpoint.annotation.Endpoint;
	import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
	import org.springframework.ws.server.endpoint.annotation.RequestPayload;
	import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
	
	import java.util.List;
	import java.util.stream.Collectors;
	
	@Endpoint
	public class ProductEndpoint {
	
		 private static final String NAMESPACE_URI = "http://www.ctiwebservice.com/xml/school";
	
		    @Autowired
		    private ProductService productService;
	
		    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductsRequest")
		    @ResponsePayload
		    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
		    	
		    	System.err.println("Received request: " + request);
		        System.err.println("Request name: " + request.getCategoryId());
		        
//		        long categoryId = request.getCategoryId();
		        // If name is null, log an error
		        if (request != null && Long.valueOf(request.getCategoryId()) != 0) {
		        	List<com.ctiwebservice.model.Product> productsByCategoryId = productService.getProductsByCategoryId(request.getCategoryId());
		        	
		        	 List<Product> responseProducts = productsByCategoryId.stream()
				                .map(p -> {
				                    Product product = new Product();
				                    product.setId(p.getId());
				                    product.setName(p.getName());
				                    product.setPrice(p.getPrice());
				                    product.setDescription(p.getDescription());
				                    product.setCategoryId(p.getCategoryId());
				                    return product;
				                })
				                .collect(Collectors.toList());
			
				        GetProductsResponse response = new GetProductsResponse();
				        
				        
				        response.getProducts().addAll(responseProducts);
				        return response;
		        }
		        
		        List<com.ctiwebservice.model.Product> products = productService.getAllProducts();
		        List<Product> responseProducts = products.stream()
		                .map(p -> {
		                    Product product = new Product();
		                    product.setId(p.getId());
		                    product.setName(p.getName());
		                    product.setPrice(p.getPrice());
		                    product.setDescription(p.getDescription());
		                    product.setCategoryId(p.getCategoryId());
		                    return product;
		                })
		                .collect(Collectors.toList());
	
		        GetProductsResponse response = new GetProductsResponse();
		        
		        
		        response.getProducts().addAll(responseProducts);
		        return response;
		    }
	}