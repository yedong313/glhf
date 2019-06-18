package com.yed.glhf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yed.glhf.common.base.IdEntity;
import io.swagger.annotations.ApiModel;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "TopicSubjectRelation对象", description = "题目主题关联表")
@TableName("topic_subject_relation")
public class TopicSubjectRelation extends IdEntity {

    private Long topicId;

    private Long subjectId;

}
