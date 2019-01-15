package com.videoworks.config;

/* spring��������
 * @author whl
 *
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//Spring��������ɨ��controller;������
@Configuration
@ComponentScan(value="com.videoworks",excludeFilters={
		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
})
public class RootConfig {

}
