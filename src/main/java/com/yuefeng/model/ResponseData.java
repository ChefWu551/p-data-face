package com.yuefeng.model;

import com.yuefeng.constant.ResponseCodeConstant;
import lombok.Data;
// todo: 装饰着模式修改，通过builder的方法来简化代码

@Data
public class ResponseData<T> {

    private long timestamp;
    private int status;
    private T data;
    private String msg;

    public ResponseData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> rd = new ResponseData<>();
        rd.setTimestamp(rd.getTimestamp());
        rd.setStatus(ResponseCodeConstant.SUCCESS.getCode());
        rd.setData(data);
        rd.setMsg(ResponseCodeConstant.SUCCESS.getMsg());

        return rd;
    }

    public static <T> ResponseData<T> fail(int code, String msg) {
        ResponseData<T> rd = new ResponseData<>();
        rd.setTimestamp(rd.getTimestamp());
        rd.setStatus(code);
        rd.setMsg(msg);

        return rd;
    }
}
