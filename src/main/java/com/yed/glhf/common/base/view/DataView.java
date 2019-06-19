package com.yed.glhf.common.base.view;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DataView extends IdView {
    /**
     * 创建时间
     */

    @Excel(name = "创建时间" ,orderNum = "1",importFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    /**
     * 最后更新时间
     */
    @Excel(name = "最后更新时间" ,orderNum = "2",importFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;
    /**
     * 是否可用，用来做逻辑删除true有效，false无效
     */
    @Excel(name = "是否删除" ,orderNum = "3", replace = {"是_0","否_1"})
    private Boolean enabled;
    /**
     * 备注
     */
    @Excel(name = "备注" ,orderNum = "4")
    private String remarks;

    @Excel(name = "乐观锁标识" ,orderNum = "5")
    private Integer version;
}
