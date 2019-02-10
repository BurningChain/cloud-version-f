package com.miracle.cloud.client;


import com.miracle.cloud.bean.User;
import com.miracle.cloud.bean.common.Response;
import com.miracle.cloud.configuration.FeignConfiguration;
import com.miracle.cloud.fallback.FeignClientApiFallback;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Component
@FeignClient(name = "miracle-service-user", fallback = FeignClientApiFallback.class, configuration = FeignConfiguration.class)
public interface FeignClientApi {


    @RequestLine("GET /user/all")
    Response<List<User>> findAllUsers();

/*
    *//**
     * 获取users
     * @return
     *//*
    @GetMapping("/getUsers")
    List<User> getUsers();

    *//**
     * 根据用户ID获取user
     * @param uuid 用户ID
     * @return
     *//*
    @GetMapping("/getUserById/{uuid}")
    User getUserById(@PathVariable("uuid") String uuid);

    *//**
     * 添加用户
     * @param user 用户对象
     * @return
     *//*
    @PostMapping("/addUser")
    boolean addUser(@RequestBody User user);

    *//**
     * 根据用户ID修改用户信息
     * @param uuid 用户ID
     * @param user
     * @return
     *//*
    @PostMapping("/updateUserById/{uuid}")
    boolean updateUserById(@PathVariable("uuid") String uuid, @RequestBody User user);

    *//**
     * 根据用户ID修改用户信息
     * @param uuid 用户ID
     * @param age 用户年龄
     * @return
     *//*
    @PostMapping("/updateById/{uuid}")
    boolean updateUserAgeById(@PathVariable("uuid") String uuid, @RequestBody Integer age);

    *//**
     * 根据用户ID删除用户
     * @param uuid 用户ID
     * @return
     *//*
    @DeleteMapping("/deleteUserById/{uuid}")
    boolean deleteUserById(@PathVariable("uuid") String uuid);*/
}