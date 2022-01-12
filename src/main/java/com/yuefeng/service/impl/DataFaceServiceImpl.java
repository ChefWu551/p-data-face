package com.yuefeng.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.component.datasource.DataSourceContextHolder;
import com.yuefeng.dao.DataConfigMapper;
import com.yuefeng.exception.ObjectNotNullException;
import com.yuefeng.handle.HandlerFactory;
import com.yuefeng.model.DataConfig;
import com.yuefeng.model.DataFaceResult;
import com.yuefeng.service.DataFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import static com.yuefeng.common.ResponseCode.CONFIG_EXCEPTION;


@Service
public class DataFaceServiceImpl implements DataFaceService {

    @Autowired
    DataConfigMapper dataConfigMapper;

    @Override
    public DataFaceResult<Object> getPathResult(String path, JSONObject param) {
        DataConfig dc = dataConfigMapper.selectByPath(path);
        if (dc == null || dc.getPathTempalte() == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), CONFIG_EXCEPTION.getMsg());
        // todo: 缓存处理
        // 解析配置
        HandlerFactory th = new HandlerFactory();
//        List<Template> templates = th.getTemplates(dc.getPathTempalte());
        DataFaceResult<Object> dfr = th.handle(dc);
        // 组装结果
        // 返回结果，缓存处理(需要缓存，且缓存过期)
        if (dc.getIsCache() != 0) th.updateCache(dfr);
        return dfr;
    }

    @Override
    public int save(DataConfig dc) {
        return dataConfigMapper.insertSelective(dc);
    }

    @Override
    public DataConfig getDataConfig(String path) {
        return dataConfigMapper.selectByPath(path);
    }


}
