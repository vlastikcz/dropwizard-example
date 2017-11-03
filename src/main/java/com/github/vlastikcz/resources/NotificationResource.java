package com.github.vlastikcz.resources;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.vlastikcz.core.services.NotificationService;

import static com.github.vlastikcz.resources.NotificationResource.NOTIFICATION_BY_ID_PATH;

@Path(NOTIFICATION_BY_ID_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {
    public static final String NOTIFICATION_BY_ID_PATH = "/notification/{notificationId}";

    private final NotificationService notificationService;

    public NotificationResource(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GET
    public Response getNotificationById(final @PathParam("notificationId") UUID notificationId) {
        return notificationService.findById(notificationId)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

}