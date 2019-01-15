package com.videoworks.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/url" }, asyncSupported = true)
public class AsynDemoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// resp.setHeader("Connection", "Keep-Alive");
		resp.setContentType("text/html;charset=utf-8");

		System.out.println(req.isAsyncSupported() + "  " + req.isAsyncStarted());
		/*
		 * req.getAsyncContext(); ��ʾ����������Ǹ���request���������� ��ת����AsyncContext
		 */

		final AsyncContext ac = req.startAsync();

		// ���ó�ʱ��ʱ��
		ac.setTimeout(5 * 1000L);

		// ���ַ�ʽ
		ac.start(new Runnable() {

			public void run() {

				try {
					TimeUnit.SECONDS.sleep(3L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					PrintWriter writer = ac.getResponse().getWriter();
					writer.write("1");
					writer.flush();

					// ���ǲ��� ͬһ��AsyncContext��û�е���complete ֮ǰ�ܲ��ܶ�ε�
					// ����request ��response
					PrintWriter writer1 = ac.getResponse().getWriter();
					writer1.write("2");
					writer1.flush();

					ServletRequest request = ac.getRequest();

					request.setAttribute("isAsyn", true);

					/*
					 * 2.�ڵ�����complete֮�� ��ʾ����첽�Ѿ������� ����ڵ��� getRequest ������getResponse�Ļ�
					 * �����׳�IllegalStateException
					 * 
					 */
					ac.complete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// ���ü���
		ac.addListener(new AsyncListenerImpl());

		// ��ͬһ��request�в���ͬʱ���ö��
		// req.startAsync();
		PrintWriter out = resp.getWriter();
		out.write("hello async");
		out.write("<br/>");
		// ����flush ��Ȼ���ǲ������ ��Ϊû�н�����ˢ��ȥ
		out.flush();
	}

	static class AsyncListenerImpl implements AsyncListener {

		public void onComplete(AsyncEvent event) throws IOException {

			System.out.println("onComplete");
		}

		public void onTimeout(AsyncEvent event) throws IOException {
			System.out.println("onTimeout");
			event.getAsyncContext().complete();
		}

		public void onError(AsyncEvent event) throws IOException {
			System.out.println("onError");
		}

		public void onStartAsync(AsyncEvent event) throws IOException {
			System.out.println("onStartAsync");
		}
	}
}