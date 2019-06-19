package com.yed.glhf.form;

import com.yed.glhf.common.base.form.DataForm;
import com.yed.glhf.common.enums.YesOrNoEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 游戏用户表
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GameUserForm extends DataForm {

    private String mobile;

    private YesOrNoEnum verified;

    private YesOrNoEnum gainedRedPack;
}
