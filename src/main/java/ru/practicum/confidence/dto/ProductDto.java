package ru.practicum.confidence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.practicum.confidence.model.type.ProductTypes;

@Data
@Builder
public class ProductDto {
    Long id;
    @NotBlank(message = "Имя не должно быть пустым!")
    String name;
    @Size(max = 200, message = "Описание должно быть не больше 200 символов")
    String description;
    @NotNull
    Double price;
    ProductTypes productTypes;
}
