package com.yuefeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("abc")
public class AbcTestController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String helloAbc() {
        return "my name is abc";
    }
}
