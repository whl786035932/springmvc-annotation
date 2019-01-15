package cn.videoworks.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * ��ʾ�н���к��޽����
 * �н���У�corePoolSize  maxSize    
 * 			1.��ӵ������������corePoolSize ,�ͰѶ�����������н������ѻ��п�������� �̳߳��ڻ�û�дﵽmaxSize,��new  �µ��߳�ִ�п��������
 * 			2.����ﵽmaxSize ,���п�������� ���;ܾ��µ�����
 * 
 * 
 * @author whl
 *
 */
public class UseThreadPoolExecutor1 {
	public static void main(String[] args) {
		MyReject myReject = new MyReject();
		
		//		�н����
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),myReject);
//		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
		
		
		Task task1 = new Task(1,"����1");
		Task task2 = new Task(2,"����2");
		Task task3 = new Task(3,"����3");
		Task task4 = new Task(4,"����4");
		Task task5 = new Task(5,"����5");
		Task task6 = new Task(6,"����6");
		
		threadPoolExecutor.submit(task1);
		threadPoolExecutor.submit(task2);
		threadPoolExecutor.submit(task3);
		threadPoolExecutor.submit(task4);
		threadPoolExecutor.submit(task5);
		threadPoolExecutor.submit(task6);
		
		threadPoolExecutor.shutdown();
		
	}

}
