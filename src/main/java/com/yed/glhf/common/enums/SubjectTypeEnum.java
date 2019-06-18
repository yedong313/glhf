package com.yed.glhf.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum SubjectTypeEnum implements IEnum<Integer> {
    SINGLE_CHOISE(1, "单选题"),
    MULTIPLE_CHOISE(2, "多选题"),
    TRUE_OR_FALSE(3, "判断题");

    private int value;
    private String desc;

    SubjectTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString(){
        return this.getDesc();
    }
}
