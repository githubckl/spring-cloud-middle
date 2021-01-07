package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功," + serverPort, result);

        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping(value = "/getById/{id}")
    public CommonResult getById(@PathVariable("id") long id) {
        Payment result = paymentService.getPaymentById(id);
        if (result != null) {
            return new CommonResult(200, "查询成功," + serverPort, result);
        } else {
            return new CommonResult(444, "查询失败", null);
        }
    }

    /*服务发现*/
    @GetMapping(value = "discovery")
    public Object discovery() {
//列出每个服务
        List<String> services = discoveryClient.getServices();
        for (String service :
                services) {
            log.info(service);
        }
//        列出每个服务下的实例
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance s :
                instances) {
            log.info(s.getServiceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
