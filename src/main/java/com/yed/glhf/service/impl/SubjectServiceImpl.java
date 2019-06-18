package com.yed.glhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yed.glhf.entity.Subject;
import com.yed.glhf.mapper.SubjectMapper;
import com.yed.glhf.service.ISubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 题目表 服务实现类
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

}
