package com.videoworks.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.videoworks.queue.MsgQueue;

@Controller
@RequestMapping
public class AsyncController {
	
	
	@RequestMapping("callable")
	@ResponseBody
	public Callable<String> processUpload(){
		System.out.println("主线程开始");
		Callable<String> callable = new Callable<String>() {

			public String call() throws Exception {
				System.out.println("副线程开始");
				Thread.sleep(6000);
				System.out.println("副线程结束");
				return "副success";
			}
		};
		System.out.println("主线程结束");
		return callable;
	}
	
	
	@ResponseBody
	@RequestMapping("/async01")
	public Callable<String> async01(){
		System.out.println("主线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
		
		Callable<String> callable = new Callable<String>() {
			public String call() throws Exception {
				System.out.println("副线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("副线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
				return "Callable<String> async01()";
			}
		};
		
		System.out.println("主线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
		return callable;
	}
	
	
	@RequestMapping("async02")
	@ResponseBody
	public DeferredResult<String> quotes(){
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		
		//添加到消息队列中
		MsgQueue.addDeffredResult(deferredResult);
		return deferredResult;
	}
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrder() {
		DeferredResult<String> resloveMsg = MsgQueue.resloveMsg();
		resloveMsg.setResult("处理订单");
		return "create order";
	}
	
	
	
	
}
