package com.github.vlastikcz;

import java.util.concurrent.ConcurrentHashMap;

import com.github.vlastikcz.core.repository.NotificationActionDtoRepository;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;
import com.github.vlastikcz.core.repository.UserDtoRepository;
import com.github.vlastikcz.core.services.CSVNotificationLoader;
import com.github.vlastikcz.core.services.NotificationActionService;
import com.github.vlastikcz.core.services.NotificationGroupService;
import com.github.vlastikcz.core.services.NotificationService;
import com.github.vlastikcz.core.services.UserService;

import io.dropwizard.Configuration;

public class DropwizardExampleConfiguration extends Configuration {
    private final NotificationActionDtoRepository notificationActionDtoRepository = new NotificationActionDtoRepository(new ConcurrentHashMap<>());
    private final NotificationDtoRepository notificationDtoRepository = new NotificationDtoRepository(new ConcurrentHashMap<>());
    private final UserDtoRepository userDtoRepository = new UserDtoRepository(new ConcurrentHashMap<>());

    public NotificationActionService getNotificationActionService() {
        return new NotificationActionService(notificationActionDtoRepository, notificationDtoRepository);
    }

    public NotificationService getNotificationService() {
        return new NotificationService(notificationDtoRepository);
    }

    public UserService getUserService() {
        return new UserService(userDtoRepository);
    }

    public NotificationGroupService getNotificationGroupService() {
        return new NotificationGroupService(notificationDtoRepository);
    }

    public CSVNotificationLoader getCsvNotificationLoader() {
        return new CSVNotificationLoader(notificationDtoRepository, userDtoRepository);
    }

}
