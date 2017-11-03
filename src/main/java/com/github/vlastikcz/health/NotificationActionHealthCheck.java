package com.github.vlastikcz.health;

import java.util.UUID;

import com.codahale.metrics.health.HealthCheck;
import com.github.vlastikcz.core.services.NotificationActionService;

public class NotificationActionHealthCheck extends HealthCheck {
    private final NotificationActionService notificationActionService;

    public NotificationActionHealthCheck(NotificationActionService notificationActionService) {
        this.notificationActionService = notificationActionService;
    }

    @Override
    protected Result check() throws Exception {
        notificationActionService.findByNotificationId(UUID.randomUUID());
        return Result.healthy();
    }
}

