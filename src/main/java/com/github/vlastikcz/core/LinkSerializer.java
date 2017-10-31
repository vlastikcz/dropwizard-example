package com.github.vlastikcz.core;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LinkSerializer extends JsonSerializer<List<Link>> {

    @Override
    public void serialize(List<Link> links, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (final Link link : links) {
            serializeLink(link, jsonGenerator);
        }
        jsonGenerator.writeEndArray();
    }

    private void serializeLink(Link link, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("rel", link.getRel());
        jsonGenerator.writeStringField("href", link.getUri().toString());
        jsonGenerator.writeEndObject();
    }
}