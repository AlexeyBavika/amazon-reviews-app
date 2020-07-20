package com.example.amazon.reviews.mapper;

import com.example.amazon.reviews.model.Review;
import com.example.amazon.reviews.model.dto.ReviewDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private static final int PRODUCT_ID_POSITION = 1;
    private static final int USER_ID_POSITION = 2;
    private static final int PROFILE_NAME_POSITION = 3;
    private static final int HELPFULNESS_NOMINATOR_POSITION = 4;
    private static final int HELPFULNESS_DENOMINATOR_POSITION = 5;
    private static final int SCORE_POSITION = 6;
    private static final int TIME_POSITION = 7;
    private static final int SUMMARY_POSITION = 8;
    private static final int TEXT_POSITION = 9;

    public ReviewDto getReviewDtoFromLine(String[] line) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setProductId(line[PRODUCT_ID_POSITION]);
        reviewDto.setUserId(line[USER_ID_POSITION]);
        reviewDto.setProfileName(line[PROFILE_NAME_POSITION]);
        reviewDto.setHelpfulnessNominator(Long
                .valueOf(line[HELPFULNESS_NOMINATOR_POSITION]));
        reviewDto.setHelpfulnessDenominator(Long
                .valueOf(line[HELPFULNESS_DENOMINATOR_POSITION]));
        reviewDto.setScore(Long.valueOf(line[SCORE_POSITION]));
        reviewDto.setTime(convertMillisToLocalDateTime(Long
                .valueOf(line[TIME_POSITION])));
        reviewDto.setSummary(line[SUMMARY_POSITION]);
        reviewDto.setText(line[TEXT_POSITION]);
        return reviewDto;
    }

    public Review getReviewFromReviewDto(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setProductId(reviewDto.getProductId());
        review.setUserId(reviewDto.getUserId());
        review.setProfileName(reviewDto.getProfileName());
        review.setHelpfulnessNominator(reviewDto.getHelpfulnessNominator());
        review.setHelpfulnessDenominator(review.getHelpfulnessDenominator());
        review.setScore(reviewDto.getScore());
        review.setTime(reviewDto.getTime());
        review.setSummary(reviewDto.getSummary());
        review.setText(reviewDto.getText());
        return review;
    }

    private LocalDateTime convertMillisToLocalDateTime(Long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }
}
