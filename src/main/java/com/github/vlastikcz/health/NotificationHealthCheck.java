package com.github.vlastikcz.health;

import com.codahale.metrics.health.HealthCheck;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;

public class NotificationHealthCheck extends HealthCheck {
    private final NotificationDtoRepository notificationDtoRepository;

    public NotificationHealthCheck(NotificationDtoRepository notificationDtoRepository) {
        this.notificationDtoRepository = notificationDtoRepository;
    }

    @Override
    protected Result check() throws Exception {
        notificationDtoRepository.findAll();
        return Result.healthy();
    }
}

