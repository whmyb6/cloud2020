package com.mywhm.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
    @Value("${server.port}")
    String serverPost;
    @Value("${config.info}")
    String configInfo;
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return serverPost + "\t" + configInfo;
    }
}
