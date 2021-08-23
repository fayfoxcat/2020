package com.fox.gateway;

import com.fox.gateway.filter.HostAddressKeyResolver;
import com.fox.gateway.filter.RequestTimeFilter;
import com.fox.gateway.filter.TokenFilter;
import com.fox.gateway.filter.UriKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route(r -> r.path("/time/**")
                .filters(f -> f.filter(new RequestTimeFilter())
                        .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                .uri("http://httpbin.org:80/get")
        ).build();
    }

    //@Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

    /* 请求url限流 */
    //@Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }

    /* 请求地址限流 */
    @Bean
    public HostAddressKeyResolver hostAddressKeyResolver() {
        return new HostAddressKeyResolver();
    }
}
