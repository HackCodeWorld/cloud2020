package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问 肯定ok
     * @param id
     * @return
     */
    public String paymentInfoOk(Integer id){
        return "线程池： " + Thread.currentThread().getName() + " paymentInfoOk, id: " + id + "\t" + "haha";
    }

    @HystrixCommand(fallbackMethod = "payment_timeout_handler", commandProperties = {
            @HystrixProperty (name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") //3秒正常
            //超过的话熔断 并且调用下面的handler方法
    })
    public String paymentInfo_timeout(Integer id){
        int time = 5000;
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_timeout, id: " + id + "\t" + "haha" +
                "耗时：" + time + "秒钟";
    }

    /**
     * 兜底方法处理高并发
     * @param id
     * @return
     */
    public String payment_timeout_handler(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " 服务器用户需求过大，请30秒后重新刷新界面 , id : "
                + id + "\t" + "o(T--T)0";
    }


}
