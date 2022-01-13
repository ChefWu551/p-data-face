package com.yuefeng.dao;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2022/1/12
 */
public interface BusinessDataMapper {
    List<Map<String, Object>> selectData(String statement);
}
