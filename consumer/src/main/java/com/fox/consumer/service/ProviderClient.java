package com.fox.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider")
public interface ProviderClient {

    @GetMapping("/feign-info")
    String info(@RequestParam(value = "name") String name);
}
