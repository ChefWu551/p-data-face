package com.yuefeng.handle;

import com.yuefeng.exception.ObjectNotNullException;
import com.yuefeng.handle.handler.RequestHandler;
import com.yuefeng.handle.handler.RequestHandlerEnum;
import com.yuefeng.model.DataConfig;
import com.yuefeng.model.DataFaceConfig;
import com.yuefeng.model.DataFaceResult;
import com.yuefeng.model.SeriesConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static com.yuefeng.common.ResponseCode.CONFIG_EXCEPTION;

/**
 * 处理工厂，处理模板，返回自定义结果
 */
@Slf4j
public class HandlerFactory {

//    private DataFaceResult<Object> dfr;
//
//    /**
//     * 处理模板入口，返回结果信息
//     * @param dc
//     * @return
//     */
//    public DataFaceResult<Object> handle(DataConfig dc) {
//        return getFaceConfig(dc.getPathTempalte());
//    }
//
//    // todo: 处理缓存
//    public void updateCache(DataFaceResult<Object> dfr) {
//
//    }
//
//    // 解析配置
//    public DataFaceResult<Object> getFaceConfig(String pathTempalte) {
//        DataFaceConfig dfc = InterfaceConfig.getTemplates(pathTempalte);
//        dfr = new DataFaceResult<>();
//        dfr.setTitle(dfc.getTitle());
//        dfr.setSubTitle(dfc.getSubTitle());
//
//        registerSeriesConfigs(dfc);
//
//
//        return dfr;
//    }
//
//    // todo: 代理模式来处理优化
//    public void registerSeriesConfigs(DataFaceConfig dfc) {
//        if (dfc.getList() != null) {
//            getData(dfc.getList());
//            dfr.setRObject(new Object());
//        }
//
//        if (dfc.getAxis() != null) {
//
//        }
//    }
//
//    // 处理请求类型，获取结果
//    @SneakyThrows
//    public void getData(SeriesConfig sc) {
//        RequestHandlerEnum rhe = RequestHandlerEnum.typeOf(sc.getData().getHandler());
//        if (rhe == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), "未知的数据处理器");
//
//        RequestHandler rh = (RequestHandler) rhe.getHandlerClass().getConstructor().newInstance();
//        rh.handle(sc.getData().getParams());
//        log.info("处理器是：" + rh.getClass());
//    }
}
