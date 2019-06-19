package com.yed.glhf.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yed.glhf.common.base.entity.IdEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 题目主题关联表
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TopicSubjectRelation对象", description = "题目主题关联表")
@TableName("topic_subject_relation")
public class TopicSubjectRelation extends IdEntity {

    @ApiModelProperty(value = "主题id")
    private Long topicId;

    @ApiModelProperty(value = "题目id")
    private Long subjectId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
