package com.github.vlastikcz;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.vlastikcz.resources.NotificationActionResource;
import com.github.vlastikcz.resources.NotificationNotificationActionResource;
import com.github.vlastikcz.resources.NotificationResource;
import com.github.vlastikcz.resources.UserNotificationGroupResource;
import com.github.vlastikcz.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardExampleApplication extends Application<DropwizardExampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardExample";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardExampleConfiguration> bootstrap) {
    }

    @Override
    public void run(final DropwizardExampleConfiguration configuration,
                    final Environment environment) {
        configuration.getCsvNotificationLoader().load();
        environment.jersey().register(new NotificationResource(configuration.getNotificationService()));
        environment.jersey().register(new NotificationActionResource(configuration.getNotificationActionService()));
        environment.jersey().register(new UserNotificationGroupResource(configuration.getNotificationGroupService()));
        environment.jersey().register(new NotificationNotificationActionResource(configuration.getNotificationActionService()));
        environment.jersey().register(new UserResource(configuration.getUserService()));
        //environment.healthChecks().register("notification", new NotificationHealthCheck(notificationDtoRepository));
        //environment.healthChecks().register("notificationAction", new NotificationActionHealthCheck(notificationActionDtoRepository));

        environment.jersey().getResourceConfig()
                .packages("org.glassfish.jersey.examples.linking")
                .register(DeclarativeLinkingFeature.class);

        environment.getObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

}
