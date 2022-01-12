package com.yuefeng.dao;

import com.yuefeng.model.DataConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

//@Mapper
public interface DataConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataConfig record);

    int insertSelective(DataConfig record);

    DataConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataConfig record);

    int updateByPrimaryKeyWithBLOBs(DataConfig record);

    int updateByPrimaryKey(DataConfig record);

    DataConfig selectByPath(String path);
}