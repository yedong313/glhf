package com.yed.glhf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yed.glhf.common.base.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "GameUser对象", description = "游戏用户表")
@TableName("game_user")
public class GameUser extends DataEntity {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "是否验证")
    private Integer verified;

    @ApiModelProperty(value = "是否领取过红包")
    private Integer gainedRedPack;
}
