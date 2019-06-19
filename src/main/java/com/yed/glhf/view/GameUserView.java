package com.yed.glhf.view;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.yed.glhf.common.base.view.DataView;
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
public class GameUserView extends DataView {

    @ExcelEntity
    @Excel(name = "是否删除" ,orderNum = "6")
    private String mobile;

    @Excel(name = "是否验证" ,orderNum = "7")
    private YesOrNoEnum verified;

    @Excel(name = "是否领取过红包" ,orderNum = "8")
    private YesOrNoEnum gainedRedPack;
}
