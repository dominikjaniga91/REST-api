package com.epam.user;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dominik_Janiga
 */
@Service
class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ConversionService conversionService;

    UserServiceImpl(UserRepository repository,
                    ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        ConversionService conversionService1 = this.conversionService;
        User user = conversionService1.convert(userRequestDto, User.class);
        User savedUser = this.repository.save(user);
        return this.conversionService.convert(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        User user = this.conversionService.convert(userRequestDto, User.class);
        User savedUser = this.repository.save(user);
        return this.conversionService.convert(savedUser, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = this.repository.findById(id).orElseThrow(IllegalArgumentException::new);
        return this.conversionService.convert(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUser() {
        List<User> users = this.repository.findAll();
        return users.stream()
                .map(u -> this.conversionService.convert(u, UserResponseDto.class))
                .collect(Collectors.toList());
    }
}
