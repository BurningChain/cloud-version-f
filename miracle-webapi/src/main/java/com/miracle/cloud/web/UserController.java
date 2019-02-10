package com.miracle.cloud.web;

import com.miracle.cloud.bean.User;
import com.miracle.cloud.bean.common.Response;
import com.miracle.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Response<List<User>> getAll() {
        return new Response<>(200, "success", userService.findAll());
    }
}
