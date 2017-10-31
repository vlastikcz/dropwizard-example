package com.github.vlastikcz.health;

import com.codahale.metrics.health.HealthCheck;
import com.github.vlastikcz.db.NotificationDAO;

public class NotificationHealthCheck extends HealthCheck {
    private final NotificationDAO notificationDAO;

    public NotificationHealthCheck(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @Override
    protected Result check() throws Exception {
        notificationDAO.findAll();
        return Result.healthy();
    }
}

