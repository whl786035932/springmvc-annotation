package com.videoworks.queue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.web.context.request.async.DeferredResult;

public class MsgQueue {
	private static LinkedBlockingQueue<DeferredResult<String>> queue = new LinkedBlockingQueue<DeferredResult<String>>();
	
	public static void addDeffredResult(DeferredResult<String> result) {
		queue.add(result);
	}
	
	public static DeferredResult<String> resloveMsg() {
		DeferredResult<String> poll = queue.poll();
		return poll ;
	}

}
