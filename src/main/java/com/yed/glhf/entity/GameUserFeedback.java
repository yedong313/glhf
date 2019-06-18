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
 * 用户反馈意见表
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GameUserFeedback对象", description = "用户反馈意见表")
@TableName("game_user_feedback")
public class GameUserFeedback extends DataEntity {

    @ApiModelProperty(value = "游戏用户反馈意见")
    private String feedBack;

    @ApiModelProperty(value = "game_user主键")
    private Long gameUserId;
}
