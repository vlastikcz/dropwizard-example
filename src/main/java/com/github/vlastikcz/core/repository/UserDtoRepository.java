package com.github.vlastikcz.core.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.github.vlastikcz.core.dto.UserDto;

import static java.util.Objects.requireNonNull;

public class UserDtoRepository {
    private final Map<UUID, UserDto> userDtos;

    public UserDtoRepository(Map<UUID, UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public UserDto createIfNotExists(UserDto userDto) {
        requireNonNull(userDto, "'userDto' cannot be null");
        userDtos.put(userDto.getId(), userDto);
        return userDto;
    }

    public Optional<UserDto> findById(UUID id) {
        return Optional.ofNullable(userDtos.get(id));
    }

    public Collection<UserDto> findAll() {
        return userDtos.values();
    }
}
