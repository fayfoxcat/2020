package com.fox.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.fox.consumer.service.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.*;

@RestController
@RequestMapping("api")
public class ConsumerController {

    @Autowired
    ProviderClient providerClient;

    @GetMapping("/call-feign")
    public String callFeign() {
        return providerClient.info("feign");
    }

    @GetMapping("/flow")
    public void flow() {

    }

    @GetMapping("/hi")
    @SentinelResource(value = "qps")
    public Map<String, Object> hi(@RequestParam(value = "name", defaultValue = "forezp", required = false) String name) {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("中国");
        list.add("江苏");
        list.add("南京");
        list.add("玄武");
        map.put("id",10086);
        map.put("name","徐凤年");
        map.put("age",18);
        map.put("address",list);
        return map;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("th", "qu");
        Flux<String> manyLetters = Flux.fromIterable(words)
                .flatMap(word -> {
                    System.out.println("Step1=" + word);
                    return Flux.fromArray(word.split(""));
                }).filter(s -> {
                    System.out.println("Step2=" + s);
                    return true;
                }).filter(s -> {
                    System.out.println("Step3=" + s);
                    return true;
                });
        manyLetters.subscribe(s -> System.out.println("Result=" + s + "\n"));
    }

}