package com.yed.glhf.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yed.glhf.common.consts.CharConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    /**
     * 修改自定义消息转换器
     *
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig());
        //将fastjson添加到视图消息转换器列表内
        converters.add(0, fastJsonHttpMessageConverter);
    }

    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回的内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteEnumUsingToString
        );
        // 处理LocalDateTime 返回中 T -> 空格
        ValueFilter valueFilter = (object, name, value) -> {
            if (value instanceof LocalDateTime) {
                LocalDateTime localDateTime = (LocalDateTime) value;
                value = localDateTime.toString().replaceAll("T", CharConstant.SPACE);
            }
            return value;
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        return fastJsonConfig;
    }
}
