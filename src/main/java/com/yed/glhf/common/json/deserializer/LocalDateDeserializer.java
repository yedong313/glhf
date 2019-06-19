package com.yed.glhf.common.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.yed.glhf.common.json.time.LocalDateUtils;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    public LocalDateDeserializer() {
    }


    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        LocalDate localDate = LocalDateUtils.parse(p.getText());
        return localDate;
    }
}
