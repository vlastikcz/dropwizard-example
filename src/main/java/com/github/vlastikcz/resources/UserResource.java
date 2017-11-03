package com.github.vlastikcz.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.vlastikcz.core.services.UserService;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getAllUsers() {
        return Response.ok(userService.findAllUsers()).build();
    }

}
