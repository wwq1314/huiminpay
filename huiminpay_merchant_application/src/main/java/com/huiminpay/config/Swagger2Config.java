package com.huiminpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 指定swagger生效的基础目录
     */
    @Bean
    public Docket docket(){
        //指定swagger文档类型
        return new Docket(DocumentationType.SWAGGER_2)
                //指定生成的swagger文档的基础信息
                .apiInfo(bulidApiInfo())
                .select()
                //指定扫描的基础包路径
                .apis(RequestHandlerSelectors.basePackage("com.huiminpay.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建API基础信息【在网页显示的信息】
     * @return
     */
    public ApiInfo bulidApiInfo(){
        Contact contact = new Contact("Wwq", "", "");
        return new ApiInfoBuilder()
                .title("惠民支付API接口文档")
                .description("该文档有后端编写，共前端进行测试使用的")
                .contact(contact)
                .version("V.0.1")
                .build();

    }

}
