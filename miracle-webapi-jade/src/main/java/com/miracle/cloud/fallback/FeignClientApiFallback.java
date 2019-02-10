package com.miracle.cloud.fallback;

import com.miracle.cloud.bean.User;
import com.miracle.cloud.bean.common.Response;
import com.miracle.cloud.client.FeignClientApi;

import java.util.List;

public class FeignClientApiFallback implements FeignClientApi {
    @Override
    public Response<List<User>> findAllUsers() {
        return new Response<>(404, "服务暂时不可用！", null);
    }


}
