package com.yed.glhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yed.glhf.entity.GameUser;
import com.yed.glhf.mapper.GameUserMapper;
import com.yed.glhf.service.IGameUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 游戏用户表 服务实现类
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GameUserServiceImpl extends ServiceImpl<GameUserMapper, GameUser> implements IGameUserService {

}
