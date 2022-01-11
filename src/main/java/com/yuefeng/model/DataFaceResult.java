package com.yuefeng.model;

import lombok.Data;

/**
 * 拼接组装返回的结果
 */
@Data
public class DataFaceResult<T> {

    private String title;
    private String subTitle;

    private T rObject;
}
