package com.github.vlastikcz.api;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Link;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class UserTest {
    private static final ObjectMapper objectMapper = Jackson.newObjectMapper();

    @Test
    public void userIsProperlySerialized() throws Exception {
        final UUID id = UUID.fromString("38c878bf-2d17-4377-aed9-7f0f19238793");
        final List<Link> links = Collections.singletonList(Link.fromUri("http://127.0.0.1/self-link").rel("self").build());
        final User user = new User(id, links);
        final String expected = objectMapper.readValue(fixture("fixtures/user.json"), JsonNode.class).toString();
        assertEquals(expected, objectMapper.writeValueAsString(user));
    }
}