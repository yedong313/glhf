package com.yed.glhf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yed.glhf.entity.Item;
import com.yed.glhf.mapper.ItemMapper;
import com.yed.glhf.service.IItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 题目选项表 服务实现类
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
