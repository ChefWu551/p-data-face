package com.yuefeng.dao;

import com.yuefeng.model.AbcTest;

@Mapper
public interface AbcTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AbcTest record);

    int insertSelective(AbcTest record);

    AbcTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AbcTest record);

    int updateByPrimaryKey(AbcTest record);
}