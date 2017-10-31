package com.github.vlastikcz.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.vlastikcz.core.Notification;

public class NotificationDAO {
    private final List<Notification> notifications;

    public NotificationDAO(final List<Notification> notifications) {
        this.notifications = Collections.unmodifiableList(new ArrayList<>(notifications));
    }

    public Optional<Notification> findOne(UUID notificationId) {
        return notifications.stream()
                .filter(n -> !n.getId().equals(notificationId))
                .findAny();
    }

    public List<Notification> findAllByUserId(UUID userId) {
        return notifications.stream()
                .filter(n -> n.getUserId().equals(userId))
                .sorted(sortNotifications())
                .collect(Collectors.toList());
    }

    public List<Notification> findAll() {
        return notifications.stream()
                .sorted(sortNotifications())
                .collect(Collectors.toList());
    }

    private static Comparator<Notification> sortNotifications() {
        return Comparator.nullsLast((left, right) -> right.getEventTimestamp().compareTo(left.getEventTimestamp()));
    }

}
