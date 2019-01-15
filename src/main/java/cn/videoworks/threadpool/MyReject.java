package cn.videoworks.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyReject implements RejectedExecutionHandler{

	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("自定义处理。。。");
		System.out.println("当前被拒绝的任务："+r.toString());
	}

}
