package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.OpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
@Slf4j
public class OrderController {
    @Resource
    private OpenFeignService openFeignService;
    @GetMapping(value = "/getById/{id}")
    public CommonResult<Payment> getById(@PathVariable("id") Long id){
        return openFeignService.getById(id);
    }
    @GetMapping("/feign/timeout")
    public String paymentFeignTimeOut(){
        return openFeignService.paymentFeignTimeOut();
    }
}
