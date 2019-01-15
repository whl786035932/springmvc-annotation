package cn.videoworks.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleJoB {

	public static void main(String[] args) {
		Temp temp = new Temp();
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
		newScheduledThreadPool.scheduleWithFixedDelay(temp, 2, 3, TimeUnit.SECONDS);
	}
}
