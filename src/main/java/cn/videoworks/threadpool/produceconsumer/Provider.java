package cn.videoworks.threadpool.produceconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable {

	private BlockingQueue<Data> queue;
	private boolean isRunning=true;
	private static AtomicInteger count = new AtomicInteger(0);
	public void run() {
		
		while(isRunning) {
			int incrementAndGet = count.incrementAndGet();
			Data data = new Data(incrementAndGet,"任务"+incrementAndGet);
			queue.add(data);
			System.out.println(Thread.currentThread().getName()+"生产了"+data.getId());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public  Provider(BlockingQueue<Data> queu) {
		this.queue=queu;
	}
	
	
	public void stop() {
		isRunning=false;
	}
	
	
	
	
	
}
