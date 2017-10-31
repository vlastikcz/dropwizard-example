package com.github.vlastikcz.core;

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

public class CSVNotificationLoader {
    private static final Logger logger = LoggerFactory.getLogger(CSVNotificationLoader.class);

    public List<Notification> load() {
        final Reader in = new InputStreamReader(CSVNotificationLoader.class.getResourceAsStream("/fixtures.csv"));

        final Iterable<CSVRecord> records;
        try {
            records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
        } catch (IOException e) {
            logger.error("unable load initial data from CSV", e);
            return Collections.emptyList();
        }

        final List<Notification> result = new ArrayList<>();
        for (CSVRecord record : records) {
            String notificationGuid = record.get("notificationGuid");
            String userGuid = record.get("userGuid");
            String title = record.get("title");
            String content = record.get("content");
            String eventType = record.get("eventType");
            String eventSubtype = record.get("eventSubtype");
            String eventTimestamp = record.get("eventTimestamp");

            final Notification notification = new Notification(
                    UUID.fromString(notificationGuid),
                    UUID.fromString(userGuid),
                    title,
                    content,
                    findNotificationEventType(eventType),
                    findNotificationEventSubType(eventSubtype),
                    Instant.ofEpochMilli(Long.valueOf(eventTimestamp))
            );
            result.add(notification);
        }

        return result;
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
