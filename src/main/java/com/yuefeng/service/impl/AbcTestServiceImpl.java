package com.yuefeng.service.impl;

import com.yuefeng.dao.AbcTestMapper;
import com.yuefeng.model.AbcTest;
import com.yuefeng.service.AbcTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbcTestServiceImpl implements AbcTestService {

    @Autowired
    AbcTestMapper abcTestMapper;

    @Override
    public List<String> getAbcName() {
        return abcTestMapper.getAbcTestName();
    }

    @Override
    public Integer save(AbcTest abc) {
        return abcTestMapper.insert(abc);
    }


}
