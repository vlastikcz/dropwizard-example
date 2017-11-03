package com.github.vlastikcz;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.vlastikcz.health.NotificationActionHealthCheck;
import com.github.vlastikcz.health.NotificationHealthCheck;
import com.github.vlastikcz.resources.NotificationActionResource;
import com.github.vlastikcz.resources.NotificationResource;
import com.github.vlastikcz.resources.UserNotificationGroupResource;
import com.github.vlastikcz.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
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
        final JerseyEnvironment jersey = environment.jersey();
        jersey.register(new NotificationResource(configuration.notificationService()));
        jersey.register(new NotificationActionResource(configuration.notificationActionService(), configuration.notificationActionFactory()));
        jersey.register(new UserNotificationGroupResource(configuration.notificationGroupService()));
        jersey.register(new NotificationActionResource(configuration.notificationActionService(), configuration.notificationActionFactory()));
        jersey.register(new UserResource(configuration.userService()));
        jersey.getResourceConfig()
                .packages("org.glassfish.jersey.examples.linking")
                .register(DeclarativeLinkingFeature.class);

        environment.healthChecks().register("notification", new NotificationHealthCheck(configuration.notificationService()));
        environment.healthChecks().register("notificationAction", new NotificationActionHealthCheck(configuration.notificationActionService()));

        environment.getObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        loadInitialData(configuration);
    }

    private void loadInitialData(final DropwizardExampleConfiguration configuration) {
        configuration.csvNotificationLoader().load();
    }

}
