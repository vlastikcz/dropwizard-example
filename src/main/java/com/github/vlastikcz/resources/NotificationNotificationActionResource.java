package com.github.vlastikcz.resources;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.github.vlastikcz.api.NotificationAction;
import com.github.vlastikcz.api.NotificationActionCreateRequest;
import com.github.vlastikcz.core.services.NotificationActionService;

import static com.github.vlastikcz.resources.NotificationNotificationActionResource.NOTIFICATION_ACTION_BY_NOTIFICATION_PATH;

@Path(NOTIFICATION_ACTION_BY_NOTIFICATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationNotificationActionResource {
    public static final String NOTIFICATION_ACTION_BY_NOTIFICATION_PATH = "/notification/{notificationId}/notification-action";

    private final NotificationActionService notificationActionService;

    public NotificationNotificationActionResource(final NotificationActionService notificationActionService) {
        this.notificationActionService = notificationActionService;
    }

    @GET
    public Response getUserNotificationActions(final @PathParam("notificationId") UUID notificationId) {
        return Response.ok(notificationActionService.findByNotificationId(notificationId)).build();
    }

    @POST
    public Response create(final @PathParam("notificationId") UUID notificationId,
                           final @NotNull @Valid NotificationActionCreateRequest notificationActionCreateRequest) {
        final NotificationAction notificationAction = new NotificationAction(
                notificationActionCreateRequest.getId(),
                notificationId,
                notificationActionCreateRequest.getNotificationActionType(),
                notificationActionCreateRequest.getNotificationActionTimestamp()
        );
        final NotificationAction savedNotificationAction = notificationActionService.create(notificationAction);
        return Response.created(UriBuilder.fromResource(NotificationActionResource.class).build(savedNotificationAction.getId())).build();
    }

}