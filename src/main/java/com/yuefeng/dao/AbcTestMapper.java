package com.yuefeng.dao;

import com.yuefeng.model.AbcTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface AbcTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AbcTest record);

    int insertSelective(AbcTest record);

    AbcTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AbcTest record);

    int updateByPrimaryKey(AbcTest record);

    List<String> getAbcTestName();
}