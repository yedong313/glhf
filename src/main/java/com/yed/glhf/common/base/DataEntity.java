package com.yed.glhf.common.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import java.time.LocalDateTime;


public class DataEntity extends IdEntity {
    /**
     * 创建时间
     */
    private LocalDateTime createdDate;
    /**
     * 最后更新时间
     */
    private LocalDateTime lastModifiyDate;
    /**
     * 是否可用，用来做逻辑删除true有效，false无效
     */
    @TableLogic(value = "1", delval = "0")
    private Boolean enabled;
    /**
     * 备注
     */
    private String remarks;

    @Version
    private Integer version;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getLastModifiyDate() {
        return lastModifiyDate;
    }

    public void setLastModifiyDate(LocalDateTime lastModifiyDate) {
        this.lastModifiyDate = lastModifiyDate;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
