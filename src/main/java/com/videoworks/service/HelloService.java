package com.videoworks.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String sayHello(String string) {
		return "hello~~~"+string;
	}

}
