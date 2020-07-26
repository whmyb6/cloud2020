package com.mywhm.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA()  {
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "--------------testA";
    }
    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() +"\t testB......");
        return "--------------testB";
    }
    @GetMapping("/testD")
    public String testD(){
        log.info(Thread.currentThread().getName() + "\t 测试RT  testD.....");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return "-----testD";
    }
    @GetMapping("/testE")
    public String testE(){
        log.info(Thread.currentThread().getName() + "\t 测试异常比例  testE.....");
        int i =10/0;
        return "-----testD";
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "-------- testHotKey";
    }

//    自定义兜底方法=====
    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-----------deal_testHotKet !!!";

    }


}
