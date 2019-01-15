package com.videoworks.config;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *	spring mvc的配置类
 */
@EnableWebMvc
@ComponentScan(value="com.videoworks",includeFilters= {@Filter(type=FilterType.ANNOTATION,classes={Controller.class})},useDefaultFilters=false)
public class AppConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * 视图解析器
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	/**
	 * 配置静态资源的处理
	 * 
	 */
	
	 @Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	 
	 @Override     //若已使用静态处理则可省略该方法，pathPatterns的“/”路径是webapp下的路径，而不是/webapp/WEB-INF
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	     registry.addResourceHandler("/**").addResourceLocations("/");
	 }
	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new HandlerInterceptor() {
			
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				System.out.println("Interceptor  preHandle--------  "+request.getRequestURI());
				return true;
			}
			
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				System.out.println("Interceptor postHandle---------");
			}
			
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
					throws Exception {
				System.out.println("Interceptor  afterCompletion");
			}
		}).addPathPatterns("/**");
	}
	/**
	 * 添加一个视图
	 * 
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
	}
	/**
	 * 配置jsonconverter,@RequestBody才能接收entity,否则报415
	 */
	@Override  
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {  
        super.configureMessageConverters(converters);  
          
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();  
        jsonConverter.setDefaultCharset(Charset.forName("UTF-8"));  
          
        converters.add(jsonConverter);  
    }  
	
	
	
	
}
