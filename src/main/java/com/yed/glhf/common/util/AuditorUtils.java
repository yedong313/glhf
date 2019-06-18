package com.yed.glhf.common.util;

import com.yed.glhf.common.base.DataEntity;

import java.time.LocalDateTime;

public class AuditorUtils {

    public static void initCreatedInfo(DataEntity dataEntity) {
        LocalDateTime now = LocalDateTime.now();
        dataEntity.setEnabled(true);
        dataEntity.setVersion(1);
        dataEntity.setCreatedDate(now);
        dataEntity.setLastModifiyDate(now);
    }

    public static void initLastModifiedInfo(DataEntity dataEntity) {
        LocalDateTime now = LocalDateTime.now();
        dataEntity.setLastModifiyDate(now);
    }
}
