package com.ctiwebservice.endpoints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ctiwebservice.model.Product;
import com.ctiwebservice.service.ProductService;
import com.ctiwebservice.xml.school.GetProductsRequest;
import com.ctiwebservice.xml.school.GetProductsResponse;

import java.util.List;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.ctiwebservice.com/xml/school";

    @Autowired
    private ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        List<Product > products = productService.getAllProducts();
        GetProductsResponse response = new GetProductsResponse();
        response.setProducts(products);
        return response;
    }
}