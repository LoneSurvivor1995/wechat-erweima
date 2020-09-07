package com.example.wechaterweima.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: meihao
 * @CreateDate: 2020/9/1 16:12
 */
@Configuration
public class Config extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        //1.添加CORS配置信息
//        CorsConfiguration config = new CorsConfiguration();
//        //放行哪些原始域
//        config.addAllowedOrigin("*");
//        //是否发送Cookie信息
//        config.setAllowCredentials(true);
//        //放行哪些原始域(请求方式)
//        config.addAllowedMethod("*");
//        //放行哪些原始域(头部信息)
//        config.addAllowedHeader("*");
//        //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
//        config.addExposedHeader("**");
//
//        //2.添加映射路径
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", config);
//
//        //3.返回新的CorsFilter.
//        return new CorsFilter(configSource);
//    }
}
