package ru.practicum.confidence.service;

import ru.practicum.confidence.dto.UserDto;

public interface UserService {
    UserDto create(UserDto userDto,Long userId);

    UserDto updateUser(UserDto userDto,Long userId);

    void deleteUser(Long userId);

    UserDto getById(Long userId);
}
