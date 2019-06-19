package com.yed.glhf.view;

import com.yed.glhf.common.base.view.DataView;
import com.yed.glhf.common.enums.YesOrNoEnum;
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
public class ItemView extends DataView {

    private String descript;

    private YesOrNoEnum mark;

}
