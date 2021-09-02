package com.huiminpay.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.huiminpay.**.mapper")
public class MybatisPlusConfig {
    /**
     * 创建拦截器对象，用于实现分页插件（学习pageahelper时配置的拦截器）
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }
}
