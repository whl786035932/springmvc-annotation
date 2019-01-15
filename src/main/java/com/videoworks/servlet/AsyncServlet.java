package com.videoworks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = "/async")
public class AsyncServlet extends HttpServlet {
	@Override
	protected void doGet(final HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		// 支持异步请求
		long beginMainMissions = System.currentTimeMillis();
		System.out.println("主线程开始。。。" + Thread.currentThread() + "==>" + beginMainMissions+"-------"+req.getRequestURI());
		final AsyncContext startAsync = req.startAsync();
		startAsync.start(new Runnable() {
			public void run() {
				try {
					long aysnc_currentTimeMillis = System.currentTimeMillis();
					System.out.println("副线程开始。。。" + Thread.currentThread() + "==>" + aysnc_currentTimeMillis);
					sayHello();
					// 获取到异步上下文
					AsyncContext asyncContext = req.getAsyncContext();
					// 4、获取响应
					ServletResponse response = asyncContext.getResponse();
					ServletRequest request = asyncContext.getRequest();
					
					
					
					PrintWriter writer1 = startAsync.getResponse().getWriter();
					writer1.write("22222222222222");
					writer1.flush();

					
					long async_endcurrentTimeMillis = System.currentTimeMillis();
					long async_useMillions=async_endcurrentTimeMillis-aysnc_currentTimeMillis;
					System.out.println("副线程结束。。。" + Thread.currentThread() + "==>" +async_useMillions );
					startAsync.complete();
				} catch (Exception e) {
				}
			}
		});
		
		long endMissions = System.currentTimeMillis();
		long useMillions = endMissions-beginMainMissions;
		System.out.println("主线程结束。。。"+Thread.currentThread()+"==>"+useMillions);
		PrintWriter out = resp.getWriter();
		out.write("hello async");
		out.write("<br/>");
		// 调用flush 不然还是不会输出 因为没有将内容刷出去
		out.flush();
		
	}

	public void sayHello() throws Exception {
		System.out.println(Thread.currentThread() + " processing...");
		Thread.sleep(6000);
	}
}
