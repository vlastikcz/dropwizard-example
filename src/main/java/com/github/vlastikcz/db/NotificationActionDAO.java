package com.github.vlastikcz.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.core.NotificationAction;

public class NotificationActionDAO {
    private final List<NotificationAction> notificationActions;

    public NotificationActionDAO(final List<NotificationAction> notificationActions) {
        this.notificationActions = new ArrayList<>(notificationActions);
    }

    public Optional<NotificationAction> findById(UUID id) {
        return notificationActions.stream()
                .filter(a -> a.getNotificationActionId().equals(id))
                .findAny();
    }

    public List<NotificationAction> findByNotificationId(UUID notificationId) {
        return notificationActions.stream()
                .filter(a -> a.getNotificationId().equals(notificationId))
                .collect(Collectors.toList());
    }

    public NotificationAction create(final NotificationAction notificationAction) {
        Objects.requireNonNull(notificationAction, "'notificationAction' cannot be null");
        notificationActions.add(notificationAction);
        return notificationAction;
    }

}
