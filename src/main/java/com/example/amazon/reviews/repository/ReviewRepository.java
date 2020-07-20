package com.example.amazon.reviews.repository;

import com.example.amazon.reviews.model.Product;
import com.example.amazon.reviews.model.Review;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.userId = :id")
    List<Review> findByUserId(@Param("id") String id);

    @Query("SELECT new Product(r.productId) FROM Review r GROUP BY r.productId "
            + "ORDER BY COUNT(r.productId) DESC")
    List<Product> findMostCommentedProducts(PageRequest p);

    @Query("SELECT r.text FROM Review r")
    List<String> getAllTexts();
}
