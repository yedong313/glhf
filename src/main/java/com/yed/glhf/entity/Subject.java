package com.yed.glhf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yed.glhf.common.base.DataEntity;
import com.yed.glhf.common.enums.SubjectTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Subject对象", description = "题目表")
@TableName("subject")
public class Subject extends DataEntity {

    @ApiModelProperty(value = "题目名称")
    private String name;

    @ApiModelProperty(value = "题目类型")
    private SubjectTypeEnum type;

    @ApiModelProperty(value = "题目描述")
    private String descript;

    @ApiModelProperty(value = "总共答题次数")
    private Long allTime;

    @ApiModelProperty(value = "答对次数")
    private Long rightTime;

    @ApiModelProperty(value = "答错次数")
    private Long wrongTime;

}
