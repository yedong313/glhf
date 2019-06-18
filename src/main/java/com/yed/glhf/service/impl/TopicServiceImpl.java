package com.yed.glhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yed.glhf.entity.Topic;
import com.yed.glhf.mapper.TopicMapper;
import com.yed.glhf.service.ITopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 主题表 服务实现类
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements ITopicService {

}
