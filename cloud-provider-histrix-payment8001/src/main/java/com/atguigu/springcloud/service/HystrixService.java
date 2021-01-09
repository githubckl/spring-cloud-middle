package com.atguigu.springcloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HystrixService {
    @Value("${server.port}")
    private String port;

    public String Hystrix_ok(Long id) {
        return Thread.currentThread().getName() + "\t" + port + "\t" + id;
    }

    public String Hystrix_timeOut(Long id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + "\t" + port + "\t" + id;
    }
}
