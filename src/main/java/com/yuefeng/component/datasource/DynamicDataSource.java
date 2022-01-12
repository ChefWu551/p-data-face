package com.yuefeng.component.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private Map<Object,Object> targetDataSources;
    private DruidDataSource defaultTargetDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDBType();
    }

    public Map<Object, Object> getTargetDataSources() {
        return targetDataSources;
    }

    public void setDefaultTargetDataSource(DruidDataSource defaultTargetDataSource) {
        this.defaultTargetDataSource = defaultTargetDataSource;
    }

    public DruidDataSource getDefaultTargetDataSource() {
        return defaultTargetDataSource;
    }

}
