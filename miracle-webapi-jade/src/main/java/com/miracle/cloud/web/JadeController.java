package com.miracle.cloud.web;

import com.miracle.cloud.bean.Jade;
import com.miracle.cloud.bean.User;
import com.miracle.cloud.bean.common.Response;
import com.miracle.cloud.client.FeignClientApi;
import com.miracle.cloud.service.IJadeBizService;
import com.miracle.cloud.service.IJadeService;
import com.miracle.cloud.vo.UserWithJadeVO;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Import(FeignClientsConfiguration.class)
@RestController
@RequestMapping("/jade")
public class JadeController {

    @Autowired
    private IJadeService jadeService;

    @Autowired
    private IJadeBizService jadeBizService;

    private FeignClientApi feignClientApi;

    @Autowired
    public JadeController(Decoder decoder, Encoder encoder, Client client, Contract contract) {

        this.feignClientApi = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                // 设置http-basic认证用户名及密码
                .requestInterceptor(new BasicAuthRequestInterceptor("miracle", "123456"))
                // 作用的feign和服务
                .target(FeignClientApi.class, "http://miracle-service-user");

    }

    @GetMapping("/user")
    public Response<List<User>> findAllUser() {
        return feignClientApi.getAll();
    }

    @GetMapping("/all")
    public UserWithJadeVO findAllJade() {
        return jadeBizService.getUsersAndJades();
    }

}
