package com.github.vlastikcz;

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
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardExampleConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
