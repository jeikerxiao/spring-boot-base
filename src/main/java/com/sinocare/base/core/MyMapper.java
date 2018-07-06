package com.sinocare.base.core;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 */
public interface MyMapper<T> extends
        ConditionMapper<T>,
        BaseMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T> {
}
