package com.github.vlastikcz.core.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.github.vlastikcz.core.dto.UserDto;

import static java.util.Objects.requireNonNull;

public class UserDtoRepository {
    private final Map<UUID, UserDto> userDtos;

    public UserDtoRepository(final Map<UUID, UserDto> userDtos) {
        this.userDtos = Objects.requireNonNull(userDtos, "'userDtos' cannot be null");
    }

    public UserDto createIfNotExists(UserDto userDto) {
        requireNonNull(userDto, "'userDto' cannot be null");
        userDtos.put(userDto.getId(), userDto);
        return userDto;
    }

    public Collection<UserDto> findAll() {
        return userDtos.values();
    }
}
