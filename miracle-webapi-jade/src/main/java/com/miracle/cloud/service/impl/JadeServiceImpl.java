package com.miracle.cloud.service.impl;

import com.miracle.cloud.bean.Jade;
import com.miracle.cloud.bean.JadeExample;
import com.miracle.cloud.mapper.JadeMapper;
import com.miracle.cloud.service.IJadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JadeServiceImpl implements IJadeService {

    @Autowired
    private JadeMapper jadeMapper;

    @Override
    public List<Jade> findAll() {
        return jadeMapper.selectByExample(null);
    }
}
