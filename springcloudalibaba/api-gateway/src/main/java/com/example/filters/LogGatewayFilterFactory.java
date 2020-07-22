package com.example.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
@Component
public class LogGatewayFilterFactory
        extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {
    public LogGatewayFilterFactory(){
        super((LogGatewayFilterFactory.Config.class));
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }
    //过滤器逻辑
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if(config.isCacheLog()){
                    System.out.println("cacheLog开启");
                }
                if(config.isConsoleLog()){
                    System.out.println("consoleLog开启");
                }
                return chain.filter(exchange);
            }
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config{
        private boolean consoleLog;
        private boolean cacheLog;
    }

}
