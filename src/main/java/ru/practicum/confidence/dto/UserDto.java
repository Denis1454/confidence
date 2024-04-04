package ru.practicum.confidence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import ru.practicum.confidence.model.Basket;

@Data
@Builder
@Setter
public class UserDto {
    Long id;
    @NotBlank(message = "Имя не должен быть пустым!")
    String name;
    @NotBlank
    @Email
    String email;
    Basket basket;
}
