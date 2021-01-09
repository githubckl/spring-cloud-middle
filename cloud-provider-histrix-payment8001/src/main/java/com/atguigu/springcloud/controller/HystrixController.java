package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class HystrixController {
    @Resource
    private HystrixService hystrixService;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String hystrix_ok(@PathVariable("id") Long id){
        String result=hystrixService.Hystrix_ok(id);
        log.info("******result:"+result);
        return result;

    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String hystrix_timeout(@PathVariable("id") Long id){
        String result=hystrixService.Hystrix_timeOut(id);
        log.info("******result:"+result);
        return result;

    }
}
