package com.github.vlastikcz.core.serializer;

import java.io.IOException;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LinkSerializer extends JsonSerializer<Link> {

    @Override
    public void serialize(Link link, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        serializeLink(link, jsonGenerator);
    }

    private void serializeLink(Link link, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("rel", link.getRel());
        jsonGenerator.writeStringField("href", link.getUri().toString());
        jsonGenerator.writeEndObject();
    }
}