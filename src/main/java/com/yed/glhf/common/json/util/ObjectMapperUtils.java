package com.yed.glhf.common.json.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.yed.glhf.common.json.deserializer.LocalDateDeserializer;
import com.yed.glhf.common.json.deserializer.LocalDateTimeDeserializer;
import com.yed.glhf.common.json.deserializer.LocalTimeDeserializer;
import com.yed.glhf.common.json.serializer.LocalDateSerializer;
import com.yed.glhf.common.json.serializer.LocalDateTimeSerializer;
import com.yed.glhf.common.json.serializer.LocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectMapperUtils {

    private static Logger logger = LoggerFactory.getLogger(ObjectMapperUtils.class);
    private static final ObjectMapper objectMapper = getObjectMapper();

    public ObjectMapperUtils() {
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static String writeValueAsString(Object object) {
        return writeValueAsString(objectMapper, object);
    }

    public static String writeValueAsString(ObjectMapper objectMapper, Object object) {
        String result = null;

        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var4) {
            logger.error(var4.getMessage());
        }

        return result;
    }

    public static <T> List<T> readValueToBeanList(String json, Class<T> clazz) {
        JavaType javaType = getObjectMapper().getTypeFactory().constructParametricType(ArrayList.class, new Class[]{clazz});
        return readValueToBeanList(objectMapper, json, javaType);
    }

    public static <T> List<T> readValueToBeanList(ObjectMapper objectMapper, String json, JavaType javaType) {
        Object t = Lists.newArrayList();

        try {
            Object o = objectMapper.readValue(json, javaType);
            if (o != null) {
                t = (List) o;
            }
        } catch (IOException var5) {
            var5.printStackTrace();
            logger.error(var5.getMessage());
        }

        return (List) t;
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        return readValue(objectMapper, json, clazz);
    }

    public static <T> T readValue(ObjectMapper objectMapper, String json, Class<T> clazz) {
        Object t = null;

        try {
            t = objectMapper.readValue(json, clazz);
        } catch (IOException var5) {
            var5.printStackTrace();
            logger.error(var5.getMessage());
        }

        return (T) t;
    }
}
