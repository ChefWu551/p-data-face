package com.yuefeng.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("handler每一段返回的结果，可能是sql/link/pileup/..")
public class DataResultSet<T> {

    T result;
}
