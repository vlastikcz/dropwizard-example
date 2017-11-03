package com.github.vlastikcz.core.services;

import java.util.UUID;

public class DefaultUuidGeneratorService implements UuidGeneratorService {
    @Override
    public UUID randomUUID() {
        return UUID.randomUUID();
    }
}
