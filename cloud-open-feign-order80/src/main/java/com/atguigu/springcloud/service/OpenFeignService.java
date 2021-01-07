package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name =  "CLOUD-PAYMENT-SERVICE")
public interface OpenFeignService {


    @GetMapping(value = "/payment/getById/{id}")
    public CommonResult getById(@PathVariable("id") long id);
}
