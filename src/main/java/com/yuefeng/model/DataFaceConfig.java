package com.yuefeng.model;

import lombok.Data;

/**
 * 第一层 - 数据模板接口配置相关信息
 *
 */
@Data
public class DataFaceConfig {

    private String title;
    private String subTitle;

    /**
     * 返回类型直接是一个列表
     */
    private SeriesConfig list;

    /**
     * 坐标轴类型
     */
    private AxisDataConfig axis;

}
