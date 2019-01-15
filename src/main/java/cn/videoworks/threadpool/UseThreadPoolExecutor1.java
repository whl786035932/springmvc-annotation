package cn.videoworks.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 演示有界队列和无界队列
 * 有界队列：corePoolSize  maxSize    
 * 			1.添加的任务如果超过corePoolSize ,就把多余的任务添到有界队列里，把还有空余的任务， 线程池内还没有达到maxSize,就new  新的线程执行空余的任务，
 * 			2.如果达到maxSize ,还有空余的任务 ，就拒绝新的任务
 * 
 * 
 * @author whl
 *
 */
public class UseThreadPoolExecutor1 {
	public static void main(String[] args) {
		MyReject myReject = new MyReject();
		
		//		有界队列
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),myReject);
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
		
		
		Task task1 = new Task(1,"任务1");
		Task task2 = new Task(2,"任务2");
		Task task3 = new Task(3,"任务3");
		Task task4 = new Task(4,"任务4");
		Task task5 = new Task(5,"任务5");
		Task task6 = new Task(6,"任务6");
		
		threadPoolExecutor.submit(task1);
		threadPoolExecutor.submit(task2);
		threadPoolExecutor.submit(task3);
		threadPoolExecutor.submit(task4);
		threadPoolExecutor.submit(task5);
		threadPoolExecutor.submit(task6);
		
		threadPoolExecutor.shutdown();
		
	}

}
