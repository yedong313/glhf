package com.yed.glhf.view;

import com.yed.glhf.common.base.view.DataView;
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
public class GameUserFeedbackView extends DataView {

    private String feedBack;

    private Long gameUserId;
}
