package com.github.vlastikcz.resources;

import java.util.Objects;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.vlastikcz.core.services.NotificationActionService;

import static com.github.vlastikcz.resources.NotificationActionResource.NOTIFICATION_ACTION_BY_ID_PATH;

@Path(NOTIFICATION_ACTION_BY_ID_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationActionResource {
    public static final String NOTIFICATION_ACTION_BY_ID_PATH = "/notification-action/{notificationActionId}";

    private final NotificationActionService notificationActionService;

    public NotificationActionResource(final NotificationActionService notificationActionService) {
        this.notificationActionService = Objects.requireNonNull(notificationActionService, "'notificationActionService' cannot be null");
    }

    @GET
    public Response getNotificationAction(final @PathParam("notificationActionId") UUID notificationActionId) {
        return notificationActionService.findById(notificationActionId)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

}