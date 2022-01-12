package com.yuefeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.model.DataConfig;
import com.yuefeng.service.DataFaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class DataFaceController {

    @Autowired
    DataFaceService service;

    @RequestMapping("**")
    public Object getFaceData(@RequestBody(required = false) JSONObject param, HttpServletRequest request) {
        String path = request.getRequestURI();
        log.info("request path is : " + path);

        return service.getPathResult(path, param);
    }

    @RequestMapping(value = "config/save", method = RequestMethod.GET)
    public int save(String name, String path, String pathTemplate) {

        DataConfig dc = DataConfig.newBuilder().name(name).path(path).pathTempalte(pathTemplate).build();
        return service.save(dc);
    }

    @RequestMapping(value = "config/get", method = RequestMethod.GET)
    public DataConfig getDataConfig(String path) {
        return service.getDataConfig(path);
    }
}
