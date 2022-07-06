package tree.moe.epet.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tree.moe.epet.intercepter.JwtInterceptor;

import java.util.Locale;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 添加jwt拦截器，并指定拦截路径
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/item/**")
                .excludePathPatterns("/cat/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/sku/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/shop/**")
                .excludePathPatterns("/comment/getComment")
                .excludePathPatterns("/comment/getCommentByPage")
                .excludePathPatterns("/video/**");
    }

    /**
     * jwt拦截器
     * */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}

