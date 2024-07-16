package com.ctiwebservice.endpoints;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ctiwebservice.xml.school.Category;
import com.ctiwebservice.service.CategoryService;
import com.ctiwebservice.xml.school.GetCategoriesRequest;
import com.ctiwebservice.xml.school.GetCategoriesResponse;

@Endpoint
public class CategoryEndpoint {
	 private static final String NAMESPACE_URI = "http://www.ctiwebservice.com/xml/school";
		
	    @Autowired
	    private CategoryService categoryService;

	    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCategoriesRequest")
	    @ResponsePayload
	    public GetCategoriesResponse getCategories(@RequestPayload GetCategoriesRequest request) {
	        
	        List<com.ctiwebservice.model.Category> categories = categoryService.getAllCategories();
	        List<Category> responseCategories = categories.stream()
	                .map(p -> {
	                	Category category = new Category();
	                    category.setId(p.getId());
	                    category.setName(p.getName());
	                    return category;
	                })
	                .collect(Collectors.toList());

	        GetCategoriesResponse response = new GetCategoriesResponse();
	        
	        
	        response.getCategories().addAll(responseCategories);
	        return response;
	    }

}
