package com.github.vlastikcz.resources;

import java.net.URI;
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
import com.github.vlastikcz.core.services.NotificationActionService;

@Path("/notification-action")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationActionResource {
    private final NotificationActionService notificationActionService;

    public NotificationActionResource(final NotificationActionService notificationActionService) {
        this.notificationActionService = notificationActionService;
    }

    @GET
    @Path("/{id}")
    public Response getUserNotificationAction(final @PathParam("id") UUID id) {
        return notificationActionService.findById(id)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response create(final @NotNull @Valid NotificationAction notificationAction) {
        final NotificationAction savedNotificationAction = notificationActionService.create(notificationAction);
        final URI savedNotificationActionLocation = UriBuilder.fromResource(NotificationActionResource.class)
                .path(NotificationActionResource.class, "getUserNotificationAction") // TODO do not use reflection
                .build(savedNotificationAction.getId());
        return Response.created(savedNotificationActionLocation).build();
    }

}