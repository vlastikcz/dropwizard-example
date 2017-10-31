package com.github.vlastikcz.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.vlastikcz.core.Notification;
import com.github.vlastikcz.db.NotificationDAO;

@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {
    private final NotificationDAO notificationDAO;

    public NotificationResource(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    @GET
    public Response getNotifications(@QueryParam("userId") UUID userId) {
        return Response.ok(findNotifications(userId)).build();
    }

    private List<Notification> findNotifications(UUID userId) {
        // TODO sort
        // TODO grouping
        // TODO tests
        return userId == null
                ? notificationDAO.findAll()
                : notificationDAO.findAllByUserId(userId);
    }

    @GET
    @Path("/{notificationId}")
    public Response getNotificationById(@PathParam("notificationId") UUID notificationId) {
        return notificationDAO.findOne(notificationId)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

}