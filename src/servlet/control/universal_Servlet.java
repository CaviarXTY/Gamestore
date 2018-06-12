package servlet.control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.data.Login_Bean;

import java.sql.*;

public class universal_Servlet extends HttpServlet {

	
	/**
	 * 0��ʼ��(�̳�)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * doGet
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 *0 ������ҳ��
	 * 
	 * @param request
	 * @param response
	 * @param s
	 *            ҳ������
	 */
	public void newPage(HttpServletRequest request,
			HttpServletResponse response, String s) {
		try {
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			out.println(s);// �������
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 0ҳ����ת
	 * 
	 * @param request
	 * @param response
	 * @param PageLocal
	 *            ҳ�����·������/xxx.jsp����
	 */
	public void skipPage(HttpServletRequest request,
			HttpServletResponse response, String PageLocal) {

		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLocal);// ת��·��
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 1������/��ʾ��������ҳ��
	 */
	public void tip(HttpServletRequest request, HttpServletResponse response,
			String tip) {
		String s = "<html><body>"
				+ tip
				+ "<br><input type=button name=Submit onclick=javascript:history.back(-1); value=������һҳ></html></body>";
		newPage(request, response, s);
	}

	/**
	 * 1(��ʾ)����
	 * 
	 * @param request
	 * @param response
	 * @param tip
	 *            ����
	 * @param where
	 *            ����
	 */
	public void windows(HttpServletRequest request,
			HttpServletResponse response, String tip, String where) {
		newPage(request, response, "<script type='text/javascript'>alert('"
				+ tip + "');window.location.href='" + where + "';</script>");
	}

}
