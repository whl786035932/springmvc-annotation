package cn.videoworks.threadpool.produceconsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer  implements Runnable{

	private BlockingQueue<Data> queue;

	public void run() {
		
		while(true) {
			try {
				Data take = queue.take();
				System.out.println(Thread.currentThread().getName()+"ฯ๛ทัมห"+take.getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Consumer(BlockingQueue<Data> queue) {
		this.queue=queue;
	}

	
}
