package com.yed.glhf.view;

import com.yed.glhf.common.base.view.DataView;
import com.yed.glhf.common.enums.SubjectTypeEnum;
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
public class SubjectView extends DataView {

    private String name;

    private SubjectTypeEnum type;

    private String descript;

    private Long allTime;

    private Long rightTime;

    private Long wrongTime;

}
