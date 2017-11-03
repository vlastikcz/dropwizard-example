package com.github.vlastikcz.core.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.vlastikcz.api.NotificationEventSubType;
import com.github.vlastikcz.api.NotificationEventType;
import com.github.vlastikcz.core.dto.NotificationDto;
import com.github.vlastikcz.core.dto.UserDto;
import com.github.vlastikcz.core.repository.NotificationDtoRepository;
import com.github.vlastikcz.core.repository.UserDtoRepository;

public class CSVNotificationLoader {
    private static final Logger logger = LoggerFactory.getLogger(CSVNotificationLoader.class);

    private final NotificationDtoRepository notificationDtoRepository;
    private final UserDtoRepository userDtoRepository;

    public CSVNotificationLoader(NotificationDtoRepository notificationDtoRepository, UserDtoRepository userDtoRepository) {
        this.notificationDtoRepository = notificationDtoRepository;
        this.userDtoRepository = userDtoRepository;
    }

    public List<NotificationDto> load() {
        final Reader in = new InputStreamReader(CSVNotificationLoader.class.getResourceAsStream("/fixtures.csv"));

        final Iterable<CSVRecord> records;
        try {
            records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        } catch (IOException e) {
            logger.error("unable load initial data from CSV", e);
            return Collections.emptyList();
        }

        final List<NotificationDto> notificationDtos = new ArrayList<>();
        for (CSVRecord record : records) {
            final UUID notificationGuid = UUID.fromString(record.get("notificationGuid"));
            final UUID userGuid = UUID.fromString(record.get("userGuid"));
            final String title = record.get("title");
            final String content = record.get("content");
            final String eventType = record.get("eventType");
            final String eventSubtype = record.get("eventSubtype");
            final String eventTimestamp = record.get("eventTimestamp");

            final NotificationDto notificationDto = new NotificationDto(
                    notificationGuid,
                    userGuid,
                    title,
                    content,
                    findNotificationEventType(eventType),
                    findNotificationEventSubType(eventSubtype),
                    Instant.ofEpochMilli(Long.valueOf(eventTimestamp))
            );
            final UserDto userDto = new UserDto(userGuid);
            notificationDtoRepository.createIfNotExists(notificationDto);
            userDtoRepository.createIfNotExists(userDto);
        }

        return notificationDtos;
    }

    private static NotificationEventSubType findNotificationEventSubType(String name) {
        try {
            return NotificationEventSubType.valueOf(name);
        } catch (IllegalArgumentException e) {
            return NotificationEventSubType.UNKNOWN;
         }
    }

    private static NotificationEventType findNotificationEventType(String name) {
        try {
            return NotificationEventType.valueOf(name);
        } catch (IllegalArgumentException e) {
            return NotificationEventType.UNKNOWN;
        }
    }
}
