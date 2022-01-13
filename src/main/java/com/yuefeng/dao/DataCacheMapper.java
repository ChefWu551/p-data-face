package com.yuefeng.dao;

import com.yuefeng.model.DataCache;

public interface DataCacheMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataCache record);

    int insertSelective(DataCache record);

    DataCache selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataCache record);

    int updateByPrimaryKeyWithBLOBs(DataCache record);

    int updateByPrimaryKey(DataCache record);

    DataCache selectByPath(String path);
}