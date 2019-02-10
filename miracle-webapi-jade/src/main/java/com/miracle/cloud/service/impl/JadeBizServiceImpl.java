package com.miracle.cloud.service.impl;

import com.miracle.cloud.bean.Jade;
import com.miracle.cloud.bean.User;
import com.miracle.cloud.bean.common.Response;
import com.miracle.cloud.client.FeignClientApi;
import com.miracle.cloud.service.IJadeBizService;
import com.miracle.cloud.service.IJadeService;
import com.miracle.cloud.vo.UserWithJadeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JadeBizServiceImpl implements IJadeBizService {

    @Autowired
    private IJadeService jadeService;

    @Autowired
    private FeignClientApi feignClientApi;

    @Override
    public UserWithJadeVO getUsersAndJades() {

        Response<List<User>> allUsers = feignClientApi.getAll();
        List<User> users = null;
        if (allUsers.getCode() == 200) {
            users = allUsers.getData();
        }
        List<Jade> jades = jadeService.findAll();
        return new UserWithJadeVO(users, jades);
    }
}
