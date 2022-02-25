package com.yuefeng.model;

import com.yuefeng.exception.ObjectNotNullException;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.util.Date;

import static com.yuefeng.constant.ResponseCodeConstant.CONFIG_EXCEPTION;


@Data
@ApiModel("数据配置基础信息")
public class DataConfig {
    private Integer id;

    private String name;

    private String path;

    private String description;

    private String parameter;

    private Integer datasourceId;

    private String datasourceName;

    private Integer isCache;

    private Integer expireTime;

    private Date createdTime;

    private Date updatedTime;

    private String pathTempalte;

    public DataConfig() {

    }

    private DataConfig(Builder builder) {
        this.setName(builder.name);
        this.setPath(builder.path);
        this.setPathTempalte(builder.pathTempalte);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String path;
        private String description;
        private String parameter;
        private Integer datasourceId;
        private String datasourceName;
        private Integer isCache;
        private Integer expireTime;
        private String pathTempalte;

        public Builder() {

        }

        public Builder name(String val) {
            if (val == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), "配置名称不能为空");
            this.name = val;
            return this;
        }

        public Builder path(String val) {
            if (val == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), "配置路径不能为空");
            this.path = val;
            return this;
        }

        public Builder pathTempalte(String val) {
            if (val == null) throw new ObjectNotNullException(CONFIG_EXCEPTION.getCode(), "配置模板不能为空");
            this.pathTempalte = val;
            return this;
        }

        public DataConfig build() {
            return new DataConfig(this);
        }
    }
}