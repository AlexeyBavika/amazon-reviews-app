package com.example.amazon.reviews.controller;

import com.example.amazon.reviews.mapper.ProductMapper;
import com.example.amazon.reviews.model.dto.ProductResponseDto;
import com.example.amazon.reviews.service.ReviewService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductMapper productMapper;

    public ReviewController(ReviewService reviewService, ProductMapper productMapper) {
        this.reviewService = reviewService;
        this.productMapper = productMapper;
    }

    @GetMapping("/most-commented-products")
    public List<ProductResponseDto> getMostCommentedProducts(@RequestParam int limit) {
        return reviewService.findMostCommentedProducts(limit).stream()
                .map(productMapper::getProductResponseDtoFromProduct)
                .collect(Collectors.toList());
    }

    @GetMapping("/most-used-words")
    public List<String> getMostUsedWords(@RequestParam int limit) {
        return reviewService.findMostUsedWords(limit);
    }
}
