package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    //normal value given
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*******插入结果：" + result);
        if (result > 0){
            return new CommonResult(200, "插入数据库成功, serverPort: " + serverPort, result);
        }else{
            return new CommonResult(444, "插入数据库失败",null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){ //or @PathVariable Long id 简写 if名字都是id
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("*******插入结果：" + paymentById);
        if (paymentById != null){
            return new CommonResult(200, "查询成功, serverPort: " + serverPort, paymentById);
        }else{
            return new CommonResult(444, "没有对于记录 id:" + id,null);
        }
    }

    /**
     * return the port
     * @return
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}