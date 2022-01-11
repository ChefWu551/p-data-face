package com.yuefeng.model;

import lombok.Data;

/**
 * 第二层的配置信息
 */
@Data
public class SeriesConfig {

    private String type;
    private String name;
    private Integer id;
    private HandlerConfig data;
    private HandlerConfig result;

    private Integer yAxisIndex;

    public void setyAxisIndex(Integer yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public Integer getyAxisIndex() {
        return yAxisIndex;
    }

}
