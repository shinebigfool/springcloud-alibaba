package com.example.service.fallback;

import com.example.domain.Product;
import com.example.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                log.error("{}",throwable);
                //容错逻辑
                Product p = new Product();
                p.setPid(-100);
                return p;
            }

            @Override
            public String reduceInventory(Integer pid, Integer number) {
                return "error";
            }
        };
    }
}
