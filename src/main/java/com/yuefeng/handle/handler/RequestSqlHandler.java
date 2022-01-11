package com.yuefeng.handle.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.model.DataResultSet;

public class RequestSqlHandler implements RequestHandler{
    @Override
    public DataResultSet handle(JSONObject dataParams) {
        String sqlTemplate = dataParams.getString("sql");
        System.out.println();

        return null;
    }
}
