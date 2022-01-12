package com.yuefeng.handle.handler;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.model.DataResultSet;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class RequestSqlHandler implements RequestHandler{

//    @Autowired
//    DataSource dataSource;

//    // todo: dataSource需要完善
//    public RequestSqlHandler() {
//        this.dataSource = null;
//    }

    @SneakyThrows
    @Override
    public DataResultSet handle(JSONObject dataParams) {
        String sqlTemplate = dataParams.getString("sql");

 /*       try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlTemplate)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            int columnCount = metaData.getColumnCount();

        }*/

        System.out.println();

        return null;
    }
}
