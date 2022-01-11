package com.yuefeng.handle.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.model.DataResultSet;

public interface RequestHandler {

    DataResultSet handle(JSONObject dataParams);

}
