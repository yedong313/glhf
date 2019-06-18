package com.yed.glhf;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.yed.glhf.mapper", annotationClass = Mapper.class)
public class GlhfApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlhfApplication.class, args);
    }

}
