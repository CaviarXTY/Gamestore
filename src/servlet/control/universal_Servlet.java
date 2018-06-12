package servlet.control;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.data.Login_Bean;

import java.sql.*;

public class universal_Servlet extends HttpServlet {

	
	/**
	 * 0初始化(继承)
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
	 *0 建立新页面
	 * 
	 * @param request
	 * @param response
	 * @param s
	 *            页面内容
	 */
	public void newPage(HttpServletRequest request,
			HttpServletResponse response, String s) {
		try {
			response.setContentType("text/html;charset=gb2312");
			PrintWriter out = response.getWriter();
			out.println(s);// 输出错误
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 0页面跳转
	 * 
	 * @param request
	 * @param response
	 * @param PageLocal
	 *            页面绝对路径（“/xxx.jsp”）
	 */
	public void skipPage(HttpServletRequest request,
			HttpServletResponse response, String PageLocal) {

		RequestDispatcher dispatcher = request.getRequestDispatcher(PageLocal);// 转发路径
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
	 * 1（测试/提示）建立新页面
	 */
	public void tip(HttpServletRequest request, HttpServletResponse response,
			String tip) {
		String s = "<html><body>"
				+ tip
				+ "<br><input type=button name=Submit onclick=javascript:history.back(-1); value=返回上一页></html></body>";
		newPage(request, response, s);
	}

	/**
	 * 1(提示)弹窗
	 * 
	 * @param request
	 * @param response
	 * @param tip
	 *            内容
	 * @param where
	 *            返回
	 */
	public void windows(HttpServletRequest request,
			HttpServletResponse response, String tip, String where) {
		newPage(request, response, "<script type='text/javascript'>alert('"
				+ tip + "');window.location.href='" + where + "';</script>");
	}

}
