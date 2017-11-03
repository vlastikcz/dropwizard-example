package com.github.vlastikcz.resources;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.vlastikcz.core.services.NotificationGroupService;

import static com.github.vlastikcz.resources.UserNotificationGroupResource.NOTIFICATION_BY_USER_PATH;

@Path(NOTIFICATION_BY_USER_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class UserNotificationGroupResource {
    public static final String NOTIFICATION_BY_USER_PATH = "/user/{userId}/notification-group";

    private final NotificationGroupService notificationGroupService;

    public UserNotificationGroupResource(final NotificationGroupService notificationGroupService) {
        this.notificationGroupService = notificationGroupService;
    }

    @GET
    public Response getNotificationGroups(@PathParam("userId") UUID userId) {
        return Response.ok(notificationGroupService.findByUserId(userId)).build();
    }
}
