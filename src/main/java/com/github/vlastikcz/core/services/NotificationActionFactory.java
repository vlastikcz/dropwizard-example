package com.github.vlastikcz.core.services;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.github.vlastikcz.api.NotificationAction;
import com.github.vlastikcz.api.NotificationActionCreateRequest;

public class NotificationActionFactory {
    private final UuidGeneratorService uuidGeneratorService;
    private final Clock clock;

    public NotificationActionFactory(UuidGeneratorService uuidGeneratorService, Clock clock) {
        this.uuidGeneratorService = uuidGeneratorService;
        this.clock = clock;
    }

    public NotificationAction fromRequest(final UUID notificationId,
                                          final NotificationActionCreateRequest notificationActionCreateRequest) {
        Objects.requireNonNull(notificationId, "'notificationId' cannot be null");
        Objects.requireNonNull(notificationActionCreateRequest, "'notificationActionCreateRequest' cannot be null");

        final UUID id = notificationActionCreateRequest.getId().orElseGet(uuidGeneratorService::randomUUID);
        final Instant timestamp = notificationActionCreateRequest.getNotificationActionTimestamp().orElseGet(clock::instant);
        return new NotificationAction(
                id,
                notificationId,
                notificationActionCreateRequest.getNotificationActionType(),
                timestamp
        );
    }

}

