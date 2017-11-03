package com.github.vlastikcz.health;

import java.util.UUID;

import com.codahale.metrics.health.HealthCheck;
import com.github.vlastikcz.core.repository.NotificationActionDtoRepository;

public class NotificationActionHealthCheck extends HealthCheck {
    private final NotificationActionDtoRepository notificationActionDtoRepository;

    public NotificationActionHealthCheck(NotificationActionDtoRepository notificationActionDtoRepository) {
        this.notificationActionDtoRepository = notificationActionDtoRepository;
    }

    @Override
    protected Result check() throws Exception {
        notificationActionDtoRepository.findById(UUID.randomUUID());
        return Result.healthy();
    }
}

