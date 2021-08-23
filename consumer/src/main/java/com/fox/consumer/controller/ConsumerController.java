package com.fox.consumer.controller;

import com.fox.consumer.service.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

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