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
import com.github.vlastikcz.core.services.NotificationActionFactory;
import com.github.vlastikcz.core.services.NotificationActionService;

import static com.github.vlastikcz.resources.NotificationActionByNotificationResource.NOTIFICATION_ACTION_BY_NOTIFICATION_PATH;
import static com.github.vlastikcz.resources.NotificationActionResource.NOTIFICATION_ACTION_BY_ID_PATH;
import static java.util.Objects.requireNonNull;

@Path(NOTIFICATION_ACTION_BY_NOTIFICATION_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationActionByNotificationResource {
    public static final String NOTIFICATION_ACTION_BY_NOTIFICATION_PATH = "/notification/{notificationId}/notification-action";

    private final NotificationActionService notificationActionService;
    private final NotificationActionFactory notificationActionFactory;

    public NotificationActionByNotificationResource(final NotificationActionService notificationActionService,
                                                    final NotificationActionFactory notificationActionFactory) {
        this.notificationActionService = requireNonNull(notificationActionService, "'notificationActionService' cannot be null");
        this.notificationActionFactory = requireNonNull(notificationActionFactory, "'notificationActionFactory' cannot be null");
    }

    @GET
    public Response getNotificationActionsByNotificationId(final @PathParam("notificationId") UUID notificationId) {
        return Response.ok(notificationActionService.findByNotificationId(notificationId)).build();
    }

    @POST
    public Response create(final @PathParam("notificationId") UUID notificationId,
                           final @NotNull @Valid NotificationActionCreateRequest notificationActionCreateRequest) {
        final NotificationAction notificationAction = notificationActionFactory.fromRequest(notificationId, notificationActionCreateRequest);
        final NotificationAction savedNotificationAction = notificationActionService.create(notificationAction);
        return Response.created(UriBuilder.fromPath(NOTIFICATION_ACTION_BY_ID_PATH).build(savedNotificationAction.getId())).build();
    }
}
