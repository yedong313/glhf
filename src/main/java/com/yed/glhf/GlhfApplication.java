package com.yed.glhf;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(value = "com.yed.glhf.mapper", annotationClass = Mapper.class)
@EnableSwagger2
public class GlhfApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlhfApplication.class, args);
    }

}
