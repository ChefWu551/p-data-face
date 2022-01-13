package com.yuefeng.handle.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.dao.BusinessDataMapper;
import com.yuefeng.model.DataResultSet;

public interface RequestHandler<T> {

    DataResultSet handle(JSONObject dataParams, T handleMap);

}
