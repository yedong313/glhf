package com.yed.glhf.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum YesOrNoEnum implements IEnum<Integer> {
    yes(1, "是"),
    no(0, "否");

    private int value;
    private String desc;

    YesOrNoEnum(int value, String desc) {
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
    public String toString() {
        return this.getDesc();
    }
}
