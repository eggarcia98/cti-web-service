package com.ctiwebservice.repository;

import com.ctiwebservice.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 List<Product> findByName(String name);
}