package ru.practicum.confidence.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.confidence.dto.UserDto;
import ru.practicum.confidence.exception.NotFoundException;
import ru.practicum.confidence.exception.UserDuplicateEmailException;
import ru.practicum.confidence.mapper.UserMapperDto;
import ru.practicum.confidence.model.User;
import ru.practicum.confidence.repository.UserRepository;
import ru.practicum.confidence.service.UserService;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final String ERR_MESSAGE_USER_NOT_FOUND = "Пользователя с таким id=%s не существует";

    private final UserRepository userRepository;

    private final UserMapperDto userMapperDto;

    @Override
    public UserDto create(UserDto userDto,Long userId) {
        checkDuplicatesEmail(userDto,userId);
        userDto.getBasket().setId(userDto.getId());
        User user = userRepository.save(userMapperDto.toDto(userDto));
        return userMapperDto.toUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Long userId) {
        User user = getUser(userDto.getId());
        checkDuplicatesEmail(userDto,userId);
        userMapperDto.updateUserDto(userDto, user);
        User updated = userRepository.save(user);
        return userMapperDto.toUserDto(updated);
    }

    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, userId));
        }
    }

    @Override
    public UserDto getById(Long userId) {
        User user = getUser(userId);
        return userMapperDto.toUserDto(user);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(ERR_MESSAGE_USER_NOT_FOUND, userId)));
    }

    private void checkDuplicatesEmail(UserDto userDto, Long userId) {
        List<User> emailExist = userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(userDto.getEmail()))
                .toList();
        if (!isEmpty(emailExist)) {
            User user = emailExist.get(0);
            if (userDto.getEmail().equals(user.getEmail()) && user.getId().equals(userId)) {
                return;
            }
            throw new UserDuplicateEmailException("Такой email уже зарегистрирован");
        }
    }
}
