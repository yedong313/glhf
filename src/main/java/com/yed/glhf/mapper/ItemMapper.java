package com.yed.glhf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yed.glhf.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 题目选项表 Mapper 接口
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@Mapper
@Component
public interface ItemMapper extends BaseMapper<Item> {

}
