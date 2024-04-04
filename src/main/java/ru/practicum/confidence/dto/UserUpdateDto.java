package ru.practicum.confidence.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {
    Long id;
    String name;
    @Email
    String email;
}
