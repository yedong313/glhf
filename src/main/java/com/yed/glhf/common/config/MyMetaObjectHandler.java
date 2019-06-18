package com.yed.glhf.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setInsertFieldValByName("createdDate", now, metaObject);
        this.setInsertFieldValByName("lastModifiedDate", now, metaObject);
        this.setInsertFieldValByName("enabled", true, metaObject);
        this.setInsertFieldValByName("version", 1, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setInsertFieldValByName("lastModifiedDate", now, metaObject);
    }
}
