package com.videoworks.webapp;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.videoworks.config.AppConfig;
import com.videoworks.config.RootConfig;

/**
 * web����������ʱ�򴴽����󣻵��÷�������ʼ��������ǰǰ�˿�����
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * ��ȡ�������������ࣻ��Spring�������ļ��� ��������
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return  new Class<?>[] {RootConfig.class};
	}

	/**
	 * ��ȡweb�����������ࣨSpringMVC�����ļ��� ��������
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return  new Class<?>[] {AppConfig.class};
	}

	 /**
	  * ��ȡDispatcherServlet��ӳ����Ϣ
	  *	 /�������������󣨰�����̬��Դ��xx.js,xx.png���������ǲ�����*.jsp��
	  *  /*����������������*.jspҳ�涼���أ�jspҳ����tomcat��jsp��������ģ�
	  *	  * @return
	  */

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}
