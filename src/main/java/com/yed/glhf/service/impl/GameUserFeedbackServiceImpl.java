package com.yed.glhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yed.glhf.entity.GameUserFeedback;
import com.yed.glhf.mapper.GameUserFeedbackMapper;
import com.yed.glhf.service.IGameUserFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户反馈意见表 服务实现类
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GameUserFeedbackServiceImpl extends ServiceImpl<GameUserFeedbackMapper, GameUserFeedback> implements IGameUserFeedbackService {

}
