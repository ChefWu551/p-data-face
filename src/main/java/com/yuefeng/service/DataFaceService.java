package com.yuefeng.service;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.model.DataConfig;

public interface DataFaceService {

    Object getPathResult(String path, JSONObject param);

    int save(DataConfig dc);
}
