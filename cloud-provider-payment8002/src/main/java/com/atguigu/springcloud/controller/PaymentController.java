package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(path = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("table[payment]插入数据："+result);
        if (result>0) {
            return new CommonResult(200,"数据插入成功,serverPort: "+serverPort,result);
        }else{
            return new CommonResult(444,"数据插入失败",null);
        }
    }

    @GetMapping(path = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment!=null) {
            return new CommonResult(200,"数据查询成功,serverPort: "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应id数据，id："+id,null);
        }
    }

    @GetMapping(path = "/payment/serverPort")
    public String serverPort(){
        return serverPort;
    }
}