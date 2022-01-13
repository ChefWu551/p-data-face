package com.yuefeng.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("最终接口返回结果")
public class DataFaceResult<T> {

    private String title;
    private String subTitle;

    private T data;
}
