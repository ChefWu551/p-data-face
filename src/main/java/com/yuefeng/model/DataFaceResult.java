package com.yuefeng.model;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("最终接口返回结果")
public class DataFaceResult<T> {

    private String title;
    private String subTitle;

    private T data;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.getTitle());
        jsonObject.put("subTitle", this.getSubTitle());
        jsonObject.put("data", data.toString());

        return jsonObject.toString();
    }
}
