package com.example.amazon.reviews.service.impl;

import com.example.amazon.reviews.mapper.ReviewMapper;
import com.example.amazon.reviews.model.dto.ReviewDto;
import com.example.amazon.reviews.service.CsvService;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvServiceImpl implements CsvService {
    private final ReviewMapper reviewMapper;

    @Autowired
    public CsvServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> parseLinesToGetReviewDto(List<String> lines) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.setMaxCharsPerColumn(-1);
        CsvParser csvParser = new CsvParser(csvParserSettings);
        lines.remove(0);
        for (String line : lines) {
            String[] reviewContent = csvParser.parseLine(line);
            reviewDtoList.add(reviewMapper.getReviewDtoFromLine(reviewContent));
        }
        return reviewDtoList;
    }
}
