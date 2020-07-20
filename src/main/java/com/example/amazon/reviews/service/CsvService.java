package com.example.amazon.reviews.service;

import com.example.amazon.reviews.model.dto.ReviewDto;
import java.util.List;

public interface CsvService {
    List<ReviewDto> parseLinesToGetReviewDto(List<String> lines);
}
