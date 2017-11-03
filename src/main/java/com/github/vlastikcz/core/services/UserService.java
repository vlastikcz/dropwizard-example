package com.github.vlastikcz.core.services;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import com.github.vlastikcz.api.User;
import com.github.vlastikcz.core.dto.UserDto;
import com.github.vlastikcz.core.repository.UserDtoRepository;

public class UserService {
    private final UserDtoRepository userDtoRepository;

    public UserService(final UserDtoRepository userDtoRepository) {
        this.userDtoRepository = Objects.requireNonNull(userDtoRepository, "'userDtoRepository' cannot be null");
    }

    public Collection<User> findAllUsers() {
        return userDtoRepository.findAll()
                .stream()
                .map(UserDto::toUser)
                .collect(Collectors.toList());
    }

}
