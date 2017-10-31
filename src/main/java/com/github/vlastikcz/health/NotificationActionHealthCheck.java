package com.github.vlastikcz.health;

import java.util.UUID;

import com.codahale.metrics.health.HealthCheck;
import com.github.vlastikcz.db.NotificationActionDAO;

public class NotificationActionHealthCheck extends HealthCheck {
    private final NotificationActionDAO notificationActionDAO;

    public NotificationActionHealthCheck(NotificationActionDAO notificationActionDAO) {
        this.notificationActionDAO = notificationActionDAO;
    }

    @Override
    protected Result check() throws Exception {
        notificationActionDAO.findById(UUID.randomUUID());
        return Result.healthy();
    }
}

