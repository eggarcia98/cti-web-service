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

    private final ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        List<com.ctiwebservice.model.Product> products = productService.getAllProducts();
        GetProductsResponse response = new GetProductsResponse();
        
        // Add products to the response
        List<Product> productList = products.stream()
                .map(product -> {
                    Product schemaProduct = new Product();
                    schemaProduct.setId(product.getId());
                    schemaProduct.setName(product.getName());
                    schemaProduct.setPrice(product.getPrice());
                    return schemaProduct;
                })
                .collect(Collectors.toList());

        response.getProducts().addAll(productList);
        return response;
    }
}