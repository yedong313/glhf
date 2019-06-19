package com.yed.glhf.common.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.yed.glhf.common.json.time.LocalTimeUtils;

import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    public LocalTimeDeserializer() {
    }

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        LocalTime localTime = LocalTimeUtils.parse(p.getText());
        return localTime;
    }
}
