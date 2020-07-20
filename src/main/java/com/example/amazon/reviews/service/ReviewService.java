package com.example.amazon.reviews.service;

import com.example.amazon.reviews.model.Product;
import com.example.amazon.reviews.model.Review;
import com.example.amazon.reviews.model.dto.ReviewDto;
import java.util.List;

public interface ReviewService {

    List<Review> saveAll(List<ReviewDto> list);

    List<Product> findMostCommentedProducts(int limit);

    List<Review> findByUserId(String id);

    List<String> findMostUsedWords(int limit);

    void delete(Long id);
}
