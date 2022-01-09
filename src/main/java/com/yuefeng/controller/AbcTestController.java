package com.yuefeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuefeng.common.ResponseData;
import com.yuefeng.model.AbcTest;
import com.yuefeng.service.AbcTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("abc")
public class AbcTestController {

    @Autowired
    AbcTestService abcTestService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloAbc() {
        return "my name is abc";
    }

    @RequestMapping(value = "name", method = RequestMethod.GET)
    public String getAbcNames() {
        return abcTestService.getAbcName().toString();
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getAbcJson() {
        JSONObject jo = new JSONObject();
        jo.put("name", "xioaming");
        jo.put("age", 10);
        return jo;
    }

    @RequestMapping(value = "fail")
    public ResponseData<JSONObject> getAbcFail() {
        JSONObject jo = new JSONObject();
        jo.put("name", "xioaming");
        jo.put("age", 10);
        int e = 1 / 0 ;
        return ResponseData.success(jo);
    }

    @RequestMapping(value = "model", method = RequestMethod.GET)
    public AbcTest getAbcModel() {
        AbcTest abcTest = new AbcTest();
        abcTest.setAge(10);
        abcTest.setName("xiaodong");
        return abcTest;
    }

    @RequestMapping(method = RequestMethod.POST, value = "save")
    public Integer save(@RequestBody AbcTest abc) {
        return abcTestService.save(abc);
    }
}
