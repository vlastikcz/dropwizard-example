package com.github.vlastikcz;

import java.util.ArrayList;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.vlastikcz.core.CSVNotificationLoader;
import com.github.vlastikcz.db.NotificationActionDAO;
import com.github.vlastikcz.db.NotificationDAO;
import com.github.vlastikcz.health.NotificationActionHealthCheck;
import com.github.vlastikcz.health.NotificationHealthCheck;
import com.github.vlastikcz.resources.NotificationActionResource;
import com.github.vlastikcz.resources.NotificationResource;

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

        final NotificationDAO notificationDAO = new NotificationDAO(new CSVNotificationLoader().load());
        final NotificationActionDAO notificationActionDAO = new NotificationActionDAO(new ArrayList<>());

        environment.jersey().register(new NotificationResource(notificationDAO));
        environment.jersey().register(new NotificationActionResource(notificationActionDAO));
        environment.healthChecks().register("notification", new NotificationHealthCheck(notificationDAO));
        environment.healthChecks().register("notificationAction", new NotificationActionHealthCheck(notificationActionDAO));

        environment.jersey().getResourceConfig()
                .packages("org.glassfish.jersey.examples.linking")
                .register(DeclarativeLinkingFeature.class);

        environment.getObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

}
