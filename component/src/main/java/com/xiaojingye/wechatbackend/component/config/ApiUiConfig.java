package com.xiaojingye.wechatbackend.component.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiUiConfig {
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("用户")
                .pathsToMatch("/user/**")
                .build();
    }
    @Bean
    public GroupedOpenApi categoryApi() {
        return GroupedOpenApi.builder()
                .group("分类")
                .pathsToMatch("/category/**")
                .build();
    }
    @Bean
    public GroupedOpenApi commodityApi() {
        return GroupedOpenApi.builder()
                .group("商品")
                .pathsToMatch("/commodity/**")
                .build();
    }
    
    @Bean
    public GroupedOpenApi cartApi() {
        return GroupedOpenApi.builder()
                .group("订单")
                .pathsToMatch("/cart/**")
                .build();
    }
}
