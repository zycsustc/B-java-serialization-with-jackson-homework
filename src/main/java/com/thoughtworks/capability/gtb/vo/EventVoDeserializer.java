package com.thoughtworks.capability.gtb.vo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EventVoDeserializer extends StdDeserializer<EventVo> {
    protected EventVoDeserializer() {
        super(EventVo.class);
    }

    @Override
    public EventVo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String id = node.get("id").toString();
        String name = node.get("name").toString();
        String type = node.get("type").toString();
        Long time = node.get("time").asLong();
        String userId = node.get("userId").toString();
        String userName = node.get("userName").toString();

        EventType eventType;
        if(type.equals("DOWNLOAD")){
            eventType = EventType.DOWNLOAD;
        } else {
            eventType = EventType.UPLOAD;
        }

        LocalDateTime timeLocal = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());

        UserVo userVo = new UserVo(userId, userName);

        return new EventVo(id, name, eventType, timeLocal, userVo);
    }
}
