package com.yuefeng.service;

import com.yuefeng.model.AbcTest;

import java.util.List;

public interface AbcTestService {

    List<String> getAbcName();

    Integer save(AbcTest abc);
}
