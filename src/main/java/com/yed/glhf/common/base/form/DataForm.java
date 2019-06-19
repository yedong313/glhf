package com.yed.glhf.common.base.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DataForm extends IdForm {
    /**
     * 创建时间
     */
    private LocalDateTime createdDate;
    /**
     * 最后更新时间
     */
    private LocalDateTime lastModifiedDate;
    /**
     * 是否可用，用来做逻辑删除true有效，false无效
     */
    private Boolean enabled;
    /**
     * 备注
     */
    private String remarks;

    private Integer version;
}
