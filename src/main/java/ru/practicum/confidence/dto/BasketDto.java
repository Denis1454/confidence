package ru.practicum.confidence.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.confidence.model.Product;

@Data
@Builder
public class BasketDto {
    private Long id;

    private Product product;
}
