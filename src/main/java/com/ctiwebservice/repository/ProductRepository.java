package com.ctiwebservice.repository;

import com.ctiwebservice.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	 List<Product> findByName(String name);
	 
	 @Query("SELECT p FROM Product p WHERE p.category.id = :category_id")
	 List<Product> findByCategoryId(@Param("category_id") Long categoryId);
}