package com.yjiu;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.yjiu.filter.HTTPBearerAuthorizeAttribute;
@SpringBootApplication
@MapperScan("com.*.mapper")
@EnableConfigurationProperties
//@EnableScheduling
public class Provider8001_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(Provider8001_App.class, args);
	}
	/**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /*@Bean
    public FilterRegistrationBean  basicFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
	    urlPatterns.add("/user/getuser");
	    registrationBean.setUrlPatterns(urlPatterns);
	    return registrationBean;
    }*/
	
	@Bean
	public FilterRegistrationBean jwtFilterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
		registrationBean.setFilter(httpBearerFilter);
		List<String> urlPatterns = new ArrayList<String>();
	    urlPatterns.add("/api/*");
	    registrationBean.setUrlPatterns(urlPatterns);
	    return registrationBean;
	}
}
