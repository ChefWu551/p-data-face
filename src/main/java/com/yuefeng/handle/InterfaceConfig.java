package com.yuefeng.handle;

import com.yuefeng.model.DataFaceConfig;

import org.yaml.snakeyaml.Yaml;

public class InterfaceConfig {


    /**
     * 将yaml配置信息处理成数据展示对象
     * @param pt 接口配置模板
     * @return 数据门面配置model
     */
    public static DataFaceConfig getTemplates(String pt) {
        Yaml yaml = new Yaml();
        return yaml.loadAs(pt, DataFaceConfig.class);
    }
}
