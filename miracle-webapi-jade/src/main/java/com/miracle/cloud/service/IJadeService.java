package com.miracle.cloud.service;

import com.miracle.cloud.bean.Jade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("jadeService")
public interface IJadeService {

    @GetMapping("/")
    List<Jade> findAll();
}
