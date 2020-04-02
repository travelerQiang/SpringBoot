package com.lzq.sharecommunity.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 添加不带数据处理的视图映射
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer config = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/test.html").setViewName("testMd");
                registry.addViewController("/com/edit").setViewName("com-input");
                registry.addViewController("/user/register").setViewName("user/register");
            }
        };
        return config;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/asserts/**","/webjars/**");
    }

    /**
     * 配置文件上传的虚拟路径
     * @return
     */
    @Bean
    public WebMvcConfigurer addResourceHandlers(){
        WebMvcConfigurer config = new WebMvcConfigurer(){
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/workspace/upload/");
            }
        };
        return config;
    }
}
