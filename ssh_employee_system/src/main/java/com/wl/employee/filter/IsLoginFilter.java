package com.wl.employee.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.employee.domain.Employee;

public class IsLoginFilter implements Filter {
	String uncheckedUrls = null;
	String userSessionKey = null;
	String redirectPage = null;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//���Ҫ�õĶ���request,response,session
				HttpServletRequest servletRequest = (HttpServletRequest)request;
				HttpServletResponse servletResponse = (HttpServletResponse)response;
				HttpSession session = servletRequest.getSession();
				//����û������uri
				String path = servletRequest.getRequestURI();
//				System.out.println(path);
				//��session�л�ȡԱ������
				Employee existEmployee = (Employee)session.getAttribute("existEmployee");
				//��¼ҳ���������
				int checkPath = path.indexOf("login");
				if (checkPath > 0) {
//					System.out.println("��¼ҳ�治�ù���");
					chain.doFilter(servletRequest, servletResponse);
					return;
				}
				//���û�л�ȡ��Ա��������ת����¼ҳ��
				if (null == existEmployee) {
//					System.out.println("û�е�¼��ת");
					servletResponse.sendRedirect("/employee/login.jsp");
				} else {
					//��ȡ��Ա�����󣬼����˴�����
//					System.out.println("�ѵ�¼��������");
					chain.doFilter(servletRequest, servletResponse);
				}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		uncheckedUrls = config.getServletContext().getInitParameter("uncheckedUrls");
		userSessionKey = config.getServletContext().getInitParameter("userSessionKey");
		redirectPage = config.getServletContext().getInitParameter("redirectPage");
	}

}
