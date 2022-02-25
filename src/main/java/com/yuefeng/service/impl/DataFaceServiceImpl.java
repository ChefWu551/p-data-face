package com.yuefeng.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuefeng.component.datasource.DataSourceContextHolder;
import com.yuefeng.dao.BusinessDataMapper;
import com.yuefeng.dao.DataCacheMapper;
import com.yuefeng.dao.DataConfigMapper;
import com.yuefeng.exception.ObjectNotNullException;
import com.yuefeng.handle.InterfaceConfig;
import com.yuefeng.handle.handler.RequestHandler;
import com.yuefeng.handle.handler.RequestHandlerEnum;
import com.yuefeng.model.*;
import com.yuefeng.service.DataFaceService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.yuefeng.constant.ResponseCodeConstant.CONFIG_EXCEPTION;


@Service
@Slf4j
public class DataFaceServiceImpl implements DataFaceService {

    @Autowired
    DataConfigMapper dataConfigMapper;

    @Autowired
    BusinessDataMapper businessDataMapper;

    @Autowired
    DataCacheMapper dataCacheMapper;

    @Override
    public DataFaceResult<Object> getPathResult(String path, JSONObject param) {
        DataConfig dc = dataConfigMapper.selectByPath(path);
        if (dc == null || dc.getPathTempalte() == null)
            throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), CONFIG_EXCEPTION.getMsg());
        // 缓存是否有效，若是，则直接返回
        DataSourceContextHolder.setDBType("abcTestDB");
        DataFaceResult<Object> dataFaceResult = getCacheValue(dc);
        if (dataFaceResult != null) return dataFaceResult;
        // 解析配置
        DataFaceResult<Object> dfr = handle(dc);
        // 返回结果，缓存处理(需要缓存，且缓存过期)
        if (dc.getIsCache() == 1) updateCache(dc, dfr);
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

    // 判断缓存是否生效中
    public DataFaceResult<Object> getCacheValue(DataConfig dc) {
        // 有缓存
        boolean isCache = dc.getIsCache() == 1;
        if (!isCache) return null;
        // 缓存存在
        DataCache dataConfig = dataCacheMapper.selectByPath(dc.getPath());
        boolean isExit = !(dataConfig == null);
        if (!isExit) return null;
        // 缓存未过期
        Date expireDate = new Date(dataConfig.getCreatedTime().getTime() + dc.getExpireTime() * 1000);

        if (!expireDate.after(new Date())) return null;
        return JSON.parseObject(dataCacheMapper.selectByPath(dc.getPath()).getResult(), DataFaceResult.class);
    }

    // 更新缓存
    public void updateCache(DataConfig dc, DataFaceResult<Object> dfr) {
        if (dc.getIsCache() == 1) {
            DataSourceContextHolder.setDBType("dataFaceDB");
            DataCache dataCache = new DataCache();
            dataCache.setPath(dc.getPath());
            dataCache.setResult(JSONObject.toJSONString(dfr));
            dataCacheMapper.insertSelective(dataCache);
        }
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
        // 处理list场景
        if (dfc.getList() != null) {
            dataResultSet = getSeriesData(dfc.getList());
            dfr.setData(dataResultSet.getResult());
        }

        // 处理axis场景
        if (dfc.getAxis() != null) {
            AxisDataConfig axisDataConfig = dfc.getAxis();
            List<SeriesConfig> seriesConfigs = axisDataConfig.getSeries().stream().sorted(Comparator.comparingInt(SeriesConfig::getId)).collect(Collectors.toList());
            Map<Integer, DataResultSet<Object>> resultSetMap = new HashMap<>();
            for (SeriesConfig seriesConfig : seriesConfigs) {
                resultSetMap.put(seriesConfig.getId(), getSeriesData(seriesConfig));
            }

            log.info("");
        }
    }

    // 处理请求类型，获取结果
    @SneakyThrows
    public DataResultSet<Object> getSeriesData(SeriesConfig sc) {
        RequestHandlerEnum rhe = RequestHandlerEnum.typeOf(sc.getData().getHandler());
        if (rhe == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), "未知的数据处理器");

        RequestHandler<Object> rh = (RequestHandler) rhe.getHandlerClass().getConstructor().newInstance();
        log.info("处理器是：" + rh.getClass());
        DataSourceContextHolder.setDBType("abcTestDB");
        return rh.handle(sc.getData().getParams(), businessDataMapper);
    }

}
