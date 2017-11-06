package com.github.vlastikcz;

import java.time.Clock;
import java.util.concurrent.ConcurrentHashMap;

import com.github.vlastikcz.core.repository.NotificationActionDtoRepository;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;
import com.github.vlastikcz.core.repository.UserDtoRepository;
import com.github.vlastikcz.core.services.CSVNotificationLoader;
import com.github.vlastikcz.core.services.DefaultUuidGeneratorService;
import com.github.vlastikcz.core.services.NotificationActionFactory;
import com.github.vlastikcz.core.services.NotificationActionService;
import com.github.vlastikcz.core.services.NotificationGroupService;
import com.github.vlastikcz.core.services.NotificationService;
import com.github.vlastikcz.core.services.UserService;
import com.github.vlastikcz.core.services.UuidGeneratorService;

import io.dropwizard.Configuration;

public class DropwizardExampleConfiguration extends Configuration {

    private final Clock clock = Clock.systemDefaultZone();
    private final UuidGeneratorService uuidGeneratorService = new DefaultUuidGeneratorService();
    private final NotificationActionDtoRepository notificationActionDtoRepository = new NotificationActionDtoRepository(new ConcurrentHashMap<>());
    private final NotificationDtoRepository notificationDtoRepository = new NotificationDtoRepository(new ConcurrentHashMap<>());
    private final UserDtoRepository userDtoRepository = new UserDtoRepository(new ConcurrentHashMap<>());

    public NotificationActionService notificationActionService() {
        return new NotificationActionService(notificationActionDtoRepository, notificationDtoRepository);
    }

    public NotificationService notificationService() {
        return new NotificationService(notificationDtoRepository);
    }

    public UserService userService() {
        return new UserService(userDtoRepository);
    }

    public NotificationGroupService notificationGroupService() {
        return new NotificationGroupService(notificationDtoRepository, notificationActionDtoRepository);
    }

    public NotificationActionFactory notificationActionFactory() {
        return new NotificationActionFactory(uuidGeneratorService, clock);
    }

    public CSVNotificationLoader csvNotificationLoader() {
        return new CSVNotificationLoader(notificationDtoRepository, userDtoRepository);
    }

}
