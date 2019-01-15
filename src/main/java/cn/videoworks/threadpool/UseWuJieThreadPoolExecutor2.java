package cn.videoworks.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * ��ʾ�޽����
 */
public class UseWuJieThreadPoolExecutor2 implements Runnable{
	//��̬��ԭ�����������֤����̵߳��̰߳�ȫ
	private static AtomicInteger count= new AtomicInteger(0);
	public void run() {
		try {
		int incrementAndGet = count.incrementAndGet();
		System.out.println("����"+incrementAndGet);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 120L, TimeUnit.SECONDS, queue);
		for (int i = 0; i < 20; i++) {
			threadPoolExecutor.submit(new UseWuJieThreadPoolExecutor2());
		}
		Thread.sleep(1000);
		System.out.println("queue size:" + queue.size());		//10
		Thread.sleep(2000);
		
	}
	
	

}
