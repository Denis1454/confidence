package ru.practicum.confidence.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.confidence.dto.UserDto;
import ru.practicum.confidence.service.UserService;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserDto create(@RequestBody @Valid UserDto userDto,Long userId) {
        return userService.create(userDto,userId);
    }

    @PatchMapping("/update")
    public UserDto UserUpdateDto(@RequestBody UserDto userDto,Long userId) {
        return userService.updateUser(userDto,userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable Long userId) {
        return userService.getById(userId);
    }
}
