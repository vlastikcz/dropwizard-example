package com.github.vlastikcz.resources;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.vlastikcz.api.User;
import com.github.vlastikcz.core.services.UserService;

import io.dropwizard.testing.junit.ResourceTestRule;

import static java.util.Collections.emptyList;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class UserResourceTest {
    private static final UserService userService = EasyMock.createMock(UserService.class);

    @ClassRule
    public static final ResourceTestRule resourceTestRule = ResourceTestRule.builder()
            .addResource(new UserResource(userService))
            .build();

    @Before
    public void setup() {
        EasyMock.reset(userService);
    }

    @Test
    public void getUsersReturnAllUsers() {
        final UUID id = UUID.fromString("191c1b2e-147e-4b1e-b81d-63b2797d2559");
        final List<Link> links = Collections.singletonList(Link.fromUri("http://127.0.0.1/self-link").rel("self").build());
        final List<User> expected = Collections.singletonList(new User(id, links));

        expect(userService.findAllUsers()).andReturn(expected);
        replay(userService);
        final List<User> actual = resourceTestRule.target("/user").request().get(new GenericType<List<User>>() {
        });
        assertEquals(expected, actual);
        verify(userService);
    }

    @Test
    public void getUsersReturnsEmptyArrayIfThereAreNoUsers() {
        expect(userService.findAllUsers()).andReturn(emptyList());
        replay(userService);
        final List<User> actual = resourceTestRule.target("/user").request().get(new GenericType<List<User>>() {
        });
        assertEquals(emptyList(), actual);
        verify(userService);
    }

}