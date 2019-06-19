package com.yed.glhf.form;

import com.alibaba.fastjson.JSON;
import com.yed.glhf.common.base.form.IdForm;
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
public class TopicSubjectRelationForm extends IdForm {

    private Long topicId;

    private Long subjectId;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
