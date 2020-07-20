//package com.example.service.fallback;
//
//import com.example.domain.Product;
//import com.example.service.ProductService;
//import feign.hystrix.FallbackFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {
//    @Override
//    public ProductService create(Throwable throwable) {
//        return pid -> {
//            log.error("{}",throwable);
//            Product p = new Product();
//            p.setPid(-100);
//            return p;
//        };
//    }
//}
