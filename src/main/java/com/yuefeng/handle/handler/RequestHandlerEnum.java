package com.yuefeng.handle.handler;

import java.net.PortUnreachableException;

/**
 * 请求处理
 */
public enum RequestHandlerEnum {

    LINK_HANDLER("link", RequestLinkHandler.class),
    SQL_HANDLER("sql", RequestSqlHandler.class);

    private String handlerType;
    private Class<?> handlerClass;

    RequestHandlerEnum(String handlerType, Class<?> handlerClass) {
        this.handlerType = handlerType;
        this.handlerClass = handlerClass;
    }

    public static RequestHandlerEnum typeOf(String type) {
        for (RequestHandlerEnum handlerEnum : values()) {
            if (handlerEnum.getHandlerType().equals(type)) return handlerEnum;
        }

        return null;
    }

    public String getHandlerType() {
        return handlerType;
    }

    public void setHandlerType(String handlerType) {
        this.handlerType = handlerType;
    }

    public Class<?> getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(Class<?> handlerClass) {
        this.handlerClass = handlerClass;
    }

}
