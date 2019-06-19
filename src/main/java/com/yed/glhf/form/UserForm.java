package com.yed.glhf.form;

import com.alibaba.fastjson.JSON;
import com.yed.glhf.common.base.form.IdForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yed
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserForm extends IdForm {

    private String name;

    private String password;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
