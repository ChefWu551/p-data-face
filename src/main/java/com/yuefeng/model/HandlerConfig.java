package com.yuefeng.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 最深一层的信息
 */
@Data
public class HandlerConfig {
    String handler;
    JSONObject params;
}
