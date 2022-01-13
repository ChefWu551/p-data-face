package com.yuefeng.handle.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.dao.BusinessDataMapper;
import com.yuefeng.model.DataResultSet;

public class RequestLinkHandler implements RequestHandler{
    @Override
    public DataResultSet handle(JSONObject dataParams, Object dataMapper) {
        System.out.println();

        return null;
    }
}
