package com.haixue.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class Swagger2 {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DEMO-SERVICE")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)//禁用默认的响应
                .globalResponseMessage(RequestMethod.POST,          //根据自己的需要设计响应
                        Arrays.asList(new ResponseMessageBuilder()
                                        .code(500)
                                        .message("500 message")
                                        .responseModel(new ModelRef("Error"))
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(403)
                                        .message("Forbidden!")
                                        .build()))
                .forCodeGeneration(false)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select() // 选择那些路径和api会生成document
                .build()
                .apiInfo(apiInfo());   //这里配置apiinfo
        //
        }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Demo-Service服务的RESTful API文档")
                .description("该文档使用Swagger2构建，更多详细信息，请访问http://swagger.io")
                .license(null)
                .licenseUrl(null)
                .contact(new Contact("importsource", "", "i***@gmail.com"))
                .version("1.0")
                .build();
        return apiInfo;
    }

}