package com.yuefeng.controller;

import com.yuefeng.service.AbcTestService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "name")
    public String getAbcNames() {
        return abcTestService.getAbcName().toString();
    }
}
