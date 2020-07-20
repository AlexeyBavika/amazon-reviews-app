package com.example.amazon.reviews.mapper;

import com.example.amazon.reviews.model.Product;
import com.example.amazon.reviews.model.dto.ProductResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto getProductResponseDtoFromProduct(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setProductId(product.getName());
        return productResponseDto;
    }
}
