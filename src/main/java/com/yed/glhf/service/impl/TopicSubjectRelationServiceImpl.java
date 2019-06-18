package com.yed.glhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yed.glhf.entity.TopicSubjectRelation;
import com.yed.glhf.mapper.TopicSubjectRelationMapper;
import com.yed.glhf.service.ITopicSubjectRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 题目主题关联表 服务实现类
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TopicSubjectRelationServiceImpl extends ServiceImpl<TopicSubjectRelationMapper, TopicSubjectRelation> implements ITopicSubjectRelationService {

}
