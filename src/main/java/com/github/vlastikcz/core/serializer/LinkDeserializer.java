package com.github.vlastikcz.core.serializer;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LinkDeserializer extends JsonDeserializer<Link> {

    @Override
    public Link deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Map<String, String> params = jsonParser.readValueAs(new TypeReference<Map<String, String>>() {
        });
        if (params == null) {
            return null;
        }
        final String uri = params.remove("href");
        final String rel = params.remove("rel");
        if (uri == null) {
            return null;
        }
        return Link.fromUri(uri).rel(rel).build();
    }
}


