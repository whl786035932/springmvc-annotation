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
		System.out.println("���߳̿�ʼ");
		Callable<String> callable = new Callable<String>() {

			public String call() throws Exception {
				System.out.println("���߳̿�ʼ");
				Thread.sleep(6000);
				System.out.println("���߳̽���");
				return "��success";
			}
		};
		System.out.println("���߳̽���");
		return callable;
	}
	
	
	@ResponseBody
	@RequestMapping("/async01")
	public Callable<String> async01(){
		System.out.println("���߳̿�ʼ..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
		
		Callable<String> callable = new Callable<String>() {
			public String call() throws Exception {
				System.out.println("���߳̿�ʼ..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("���߳̽���..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
				return "Callable<String> async01()";
			}
		};
		
		System.out.println("���߳̽���..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
		return callable;
	}
	
	
	@RequestMapping("async02")
	@ResponseBody
	public DeferredResult<String> quotes(){
		DeferredResult<String> deferredResult = new DeferredResult<String>();
		
		//��ӵ���Ϣ������
		MsgQueue.addDeffredResult(deferredResult);
		return deferredResult;
	}
	
	@RequestMapping("create")
	@ResponseBody
	public String createOrder() {
		DeferredResult<String> resloveMsg = MsgQueue.resloveMsg();
		resloveMsg.setResult("������");
		return "create order";
	}
	
	
	
	
}
