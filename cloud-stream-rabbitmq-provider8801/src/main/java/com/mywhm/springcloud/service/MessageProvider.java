package com.mywhm.springcloud.service;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;


@EnableBinding(Source.class)
public class MessageProvider implements IMessageProvider {
    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString() +" " + new Date();
        output.send(MessageBuilder.withPayload(serial).build());
        return "send ok " + serial;
    }
}
