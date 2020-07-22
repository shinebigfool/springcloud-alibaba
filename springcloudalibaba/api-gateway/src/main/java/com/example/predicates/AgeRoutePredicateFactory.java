package com.example.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BeforeRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
//名字必须是配置+RoutePredicateFactory
//继承AbstractRoutePredicateFactory<配置类>
//@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {


    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }
    //读取配置文件中的参数值 给他赋值到配置类中
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("minAge","maxAge");
    }
    //断言逻辑
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //接收前台传入的age参数
                //先判断是否为空
                String age = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if(StringUtils.isNotEmpty(age)){
                    int i = Integer.parseInt(age);
                    return i < config.getMaxAge() && i > config.getMinAge();

                }
                return false;
            }
        };
    }
    //配置类，用于接收配置文件中的对应参数
    @Data
    @NoArgsConstructor
    public static class Config {
        private int maxAge;
        private int minAge;
    }
}