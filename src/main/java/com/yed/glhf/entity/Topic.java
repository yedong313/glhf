package com.yed.glhf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yed.glhf.common.base.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 主题表
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Topic对象", description = "主题表")
@TableName("topic")
public class Topic extends DataEntity {

    @ApiModelProperty(value = "主题名称")
    private String name;
}
