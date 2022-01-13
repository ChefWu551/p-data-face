package com.yuefeng.component.datasource;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDBType();
    }

//    @SneakyThrows
//    public static Connection getConnection() {
//        return super.getConnection();
//    }

}
