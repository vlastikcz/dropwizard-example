package com.github.vlastikcz.health;

import java.util.UUID;

import com.codahale.metrics.health.HealthCheck;
import com.github.vlastikcz.core.services.NotificationService;

public class NotificationHealthCheck extends HealthCheck {
    private final NotificationService notificationService;

    public NotificationHealthCheck(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    protected Result check() throws Exception {
        notificationService.findById(UUID.randomUUID());
        return Result.healthy();
    }
}

