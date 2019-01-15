package com.videoworks.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.bean.User;
import com.videoworks.service.HelloService;

@Controller
public class HelloController<V, K> {
	@Autowired
	private HelloService helloService;
	
	
	@RequestMapping(value="hello")
	@ResponseBody
	public String hello() {
		String hello = helloService.sayHello("tomcat .........");
		return hello;
	}
	
	@RequestMapping("success")
	public String success() {
		return "successjsp";
	}
	
	@RequestMapping(value = "/obj")
    @ResponseBody
    public String obj(){
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		
		 hashMap.put("name", "whl");
		 hashMap.put("age",20);
		 return JSON.toJSONString(hashMap);
	}
	
	
	@RequestMapping(value = "/add",method=RequestMethod.POST)
    @ResponseBody
    public User testjson(@RequestBody User user){
        return user;
    }
}
