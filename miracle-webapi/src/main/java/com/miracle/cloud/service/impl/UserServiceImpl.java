package com.miracle.cloud.service.impl;

import com.miracle.cloud.bean.User;
import com.miracle.cloud.mapper.UserMapper;
import com.miracle.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userMapper.selectByExample(null);
    }
}
