package com.fox.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    @Value("${server.port}")
    String port;

    @GetMapping("/feign-info")
    public String hi(@RequestParam(value = "name") String name) {
        return "消费端：" + name + "调用服务，服务端口：" + port;
    }

    @GetMapping("/flow")
    public void flow(){

    }
}
