package com.yuefeng.handle.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.dao.BusinessDataMapper;
import com.yuefeng.model.DataResultSet;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;


public class RequestSqlHandler<T> implements RequestHandler<T>{

    @SneakyThrows
    @Override
    public DataResultSet<List<Map<String, Object>>> handle(JSONObject dataParams, Object handleMap) {

        List<Map<String, Object>> value = ((BusinessDataMapper)handleMap).selectData(dataParams.get("sql").toString());
        DataResultSet<List<Map<String, Object>>> dataResultSet = new DataResultSet<>();
        dataResultSet.setResult(value);
        return dataResultSet;
    }
}
