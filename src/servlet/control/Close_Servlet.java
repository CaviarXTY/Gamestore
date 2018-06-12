package servlet.control;

import java.io.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;


import utils.Sql_Utils;

//import utils.*;
import bean.data.*;

public class Close_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//数据初始处理
		String Jid = request.getParameter("id").trim();// 获取页面id
		
		HttpSession session = request.getSession(true);// 创建session对象
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// 获取登录状态

		if(login.getLogin_case()==false)
			windows(request, response,Jid+"登录已超时","home.jsp");//页面转发
		else if(Jid.equals(login.getLogin_name())){
			Login_Bean close = new Login_Bean();
			close.setLogin_name(Jid);//记录登陆用户
			close.setLogin_case(false);//记录登陆状态
			session.setAttribute("Login", close);//将user储存到session中
			//session.removeAttribute("Login");
			windows(request, response,Jid+"已退出登录！","home.jsp");//页面转发
		}
	}

}
