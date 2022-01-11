package com.yuefeng.model;

import lombok.Data;

import java.util.List;

/**
 * 处理返回类型 - axis
 */
@Data
public class AxisDataConfig {

    private List<SeriesConfig> xAxis;
    private List<SeriesConfig> yAxis;
    private List<SeriesConfig> series;

    // 注意：这里用lombok自动生成的get/set初始化方法是个大坑！因为命名方法和yml模板提供的不一样
    // lombok getXAxis | Yaml getxAxis
    public List<SeriesConfig> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<SeriesConfig> xAxis) {
        this.xAxis = xAxis;
    }

    public List<SeriesConfig> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<SeriesConfig> yAxis) {
        this.yAxis = yAxis;
    }
}
