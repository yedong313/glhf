package com.yed.glhf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yed.glhf.common.base.DataEntity;
import com.yed.glhf.common.enums.YesOrNoEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题目选项表
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Item对象", description = "题目选项表")
@TableName("item")
public class Item extends DataEntity {

    @ApiModelProperty(value = "选项描述")
    private String descript;

    @ApiModelProperty(value = "正确答案标识")
    private YesOrNoEnum mark;

}
