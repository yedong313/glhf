package com.yed.glhf.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DataEntity extends IdEntity {
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdDate;
    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime lastModifiedDate;
    /**
     * 是否可用，用来做逻辑删除true有效，false无效
     */
    @TableLogic(value = "1", delval = "0")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "逻辑删除标识")
    private Boolean enabled;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "乐观锁标识")
    private Integer version;
}
