package com.app.repositories;

import com.app.entites.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // Search by name with LIKE
    Page<Product> findByProductNameLike(String keyword, Pageable pageDetails);

    // Generic keyword search (case-insensitive)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

    // Filter by category id
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    // Filter by price range
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
}
