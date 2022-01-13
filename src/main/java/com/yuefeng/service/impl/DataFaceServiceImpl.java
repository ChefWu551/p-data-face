package com.yuefeng.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.component.datasource.DataSourceContextHolder;
import com.yuefeng.dao.BusinessDataMapper;
import com.yuefeng.dao.DataConfigMapper;
import com.yuefeng.exception.ObjectNotNullException;
import com.yuefeng.handle.HandlerFactory;
import com.yuefeng.handle.InterfaceConfig;
import com.yuefeng.handle.handler.RequestHandler;
import com.yuefeng.handle.handler.RequestHandlerEnum;
import com.yuefeng.model.*;
import com.yuefeng.service.DataFaceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.naming.factory.DataSourceLinkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yuefeng.common.ResponseCode.CONFIG_EXCEPTION;


@Service
@Slf4j
public class DataFaceServiceImpl implements DataFaceService {

    @Autowired
    DataConfigMapper dataConfigMapper;

    @Autowired
    BusinessDataMapper businessDataMapper;

    @Override
    public DataFaceResult<Object> getPathResult(String path, JSONObject param) {
        DataConfig dc = dataConfigMapper.selectByPath(path);
        if (dc == null || dc.getPathTempalte() == null)
            throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), CONFIG_EXCEPTION.getMsg());
        // 解析配置
        HandlerFactory th = new HandlerFactory();
        DataFaceResult<Object> dfr = handle(dc);


        // 组装结果
        // 返回结果，缓存处理(需要缓存，且缓存过期)
        if (dc.getIsCache() != 0) {
            DataSourceContextHolder.setDBType("dataFaceDB");

            updateCache(dfr);
        }
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

    /**
     * 处理模板入口，返回结果信息
     *
     * @param dc
     * @return
     */
    public DataFaceResult<Object> handle(DataConfig dc) {
        return getFaceConfig(dc.getPathTempalte());
    }

    // todo: 处理缓存
    public void updateCache(DataFaceResult<Object> dfr) {

    }

    // 解析配置
    public DataFaceResult<Object> getFaceConfig(String pathTempalte) {
        DataFaceConfig dfc = InterfaceConfig.getTemplates(pathTempalte);
        DataFaceResult<Object> dfr = new DataFaceResult<>();
        dfr.setTitle(dfc.getTitle());
        dfr.setSubTitle(dfc.getSubTitle());

        registerSeriesConfigs(dfc, dfr);


        return dfr;
    }

    // todo: 代理模式来处理优化
    public void registerSeriesConfigs(DataFaceConfig dfc, DataFaceResult<Object> dfr) {
        DataResultSet<Object> dataResultSet;
        if (dfc.getList() != null) {
            dataResultSet = getSeriesData(dfc.getList());
            dfr.setData(dataResultSet.getResult());
        }

        if (dfc.getAxis() != null) {

        }
    }

    // 处理请求类型，获取结果
    @SneakyThrows
    public DataResultSet<Object> getSeriesData(SeriesConfig sc) {
        RequestHandlerEnum rhe = RequestHandlerEnum.typeOf(sc.getData().getHandler());
        if (rhe == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), "未知的数据处理器");

        RequestHandler<Object> rh = (RequestHandler) rhe.getHandlerClass().getConstructor().newInstance();
        log.info("处理器是：" + rh.getClass());
        return rh.handle(sc.getData().getParams(), businessDataMapper);
    }

}
