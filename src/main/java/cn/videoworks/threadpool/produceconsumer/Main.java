package cn.videoworks.threadpool.produceconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

		public static <E> void main(String[] args) throws InterruptedException {
			
			ArrayBlockingQueue<Data> qeueu = new ArrayBlockingQueue<Data>(20);
			
			Provider provider1 = new Provider(qeueu);
			Provider provider2 = new Provider(qeueu);
			Provider provider3 = new Provider(qeueu);
			
			Consumer consumer1 = new Consumer(qeueu);
			Consumer consumer2 = new Consumer(qeueu);
			
			ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
			
//			Thread.sleep(1000);
			newCachedThreadPool.submit(provider1);
			newCachedThreadPool.submit(provider2);
			newCachedThreadPool.submit(provider3);
			newCachedThreadPool.submit(consumer1);
			newCachedThreadPool.submit(consumer2);
			
			Thread.sleep(5000);
			provider1.stop();
			provider2.stop();
			provider3.stop();
			
			
			
			
		}
}
