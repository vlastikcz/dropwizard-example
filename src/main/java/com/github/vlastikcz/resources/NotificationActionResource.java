package com.github.vlastikcz.resources;

import java.net.URI;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.github.vlastikcz.core.NotificationAction;
import com.github.vlastikcz.db.NotificationActionDAO;

@Path("/notification/{notificationId}/action")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationActionResource {
    private final NotificationActionDAO notificationActionDAO;

    public NotificationActionResource(NotificationActionDAO notificationActionDAO) {
        this.notificationActionDAO = notificationActionDAO;
    }

    @GET
    public Response getUserNotificationActions(@PathParam("notificationId") UUID notificationId) {
        return Response.ok(notificationActionDAO.findByNotificationId(notificationId)).build();
    }

    @GET
    @Path(("/{notificationActionId}"))
    public Response getUserNotificationAction(@PathParam("notificationId") UUID notificationId,
                                              @PathParam("notificationActionId") UUID notificationActionId) {
        return notificationActionDAO.findById(notificationActionId)
                .filter(a -> a.getNotificationId().equals(notificationId))
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @POST
    public Response createUserNotificationAction(@PathParam("notificationId") UUID notificationId, NotificationAction notificationAction) {
        //TODO validate notificationId
        // TODO notificationID in request?
        final NotificationAction newNotificationAction = new NotificationAction(notificationId, notificationAction.getNotificationActionType());
        final NotificationAction savedNotificationAction = notificationActionDAO.create(newNotificationAction);
        final URI savedNotificationActionLocation = UriBuilder.fromResource(NotificationActionResource.class)
                .path(NotificationActionResource.class, "getUserNotificationAction") // TODO do not use reflection
                .build(notificationId, savedNotificationAction.getNotificationActionId());
        return Response.created(savedNotificationActionLocation).build();
    }

}