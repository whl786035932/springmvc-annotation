package cn.videoworks.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyReject implements RejectedExecutionHandler{

	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("�Զ��崦������");
		System.out.println("��ǰ���ܾ�������"+r.toString());
	}

}
