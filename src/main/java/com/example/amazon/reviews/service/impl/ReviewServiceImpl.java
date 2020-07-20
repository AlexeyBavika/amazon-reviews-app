package com.example.amazon.reviews.service.impl;

import com.example.amazon.reviews.mapper.ReviewMapper;
import com.example.amazon.reviews.model.Product;
import com.example.amazon.reviews.model.Review;
import com.example.amazon.reviews.model.dto.ReviewDto;
import com.example.amazon.reviews.repository.ReviewRepository;
import com.example.amazon.reviews.service.ReviewService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final String WORD_DELIMITER_REGEXP = "[^a-zA-Z]+";
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<Review> saveAll(List<ReviewDto> list) {
        List<Review> result = new ArrayList<>();
        for (ReviewDto reviewDto : list) {
            Review review = reviewMapper.getReviewFromReviewDto(reviewDto);
            result.add(review);
        }
        return reviewRepository.saveAll(result);
    }

    @Override
    public List<Product> findMostCommentedProducts(int limit) {
        return reviewRepository.findMostCommentedProducts(PageRequest.of(0, limit));
    }

    @Override
    public List<Review> findByUserId(String id) {
        return reviewRepository.findByUserId(id);
    }

    @Override
    public List<String> findMostUsedWords(int limit) {
        List<String> allTexts = reviewRepository.getAllTexts();
        List<String> sortedWords = sortWords(allTexts);
        return sortedWords.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    private List<String> sortWords(List<String> allTexts) {
        List<String> words = allTexts
                .parallelStream()
                .flatMap(text -> Arrays.stream(text.split(WORD_DELIMITER_REGEXP)))
                .collect(Collectors.toList());
        Map<String, Long> map = words.parallelStream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        return map.entrySet()
                .parallelStream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
