package servlet.control;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import utils.*;
import bean.data.*;

public class Register_Servlet extends universal_Servlet {

	/**
	 * doPost 处理登录事件
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 数据初始处理
		String Jid = request.getParameter("id").trim();// 获取页面id
		String Jpw = request.getParameter("pwd").trim();// 获取页面pw
		String Jpw2 = request.getParameter("pwd2").trim();// 获取页面pw2
		String Jem = request.getParameter("em").trim();// 获取页面em
		// 数据模型处理
		Register_Bean user_Register = new Register_Bean();// 创建User用户对象
//		request.setAttribute("register", user_Register);// 将user储存到session中
		user_Register.setUserID(Jid);
		user_Register.setUserPassword(Jpw);
		user_Register.setUserEmil(Jem);
		try {
			// 数据库连接，表格获取
			Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
			s.lineDatabase("JSP");// 连接数据库
			// 数据检查
			if (Jid.equals("") || Jpw.equals("") || Jpw2.equals("")|| Jem.equals("")) {
				//skipPage(request, response, "/register.jsp");
				windows(request, response,"输入不能为空!","home.jsp");//新建弹窗
				return;
			} else {
				ResultSet jrs = s.getTable("Users");// 获取表格
				s.setWrite();//设置表格集可写入
				while (jrs.next()) {// 遍历验证
					if(Jid.equals(jrs.getString(1))){//账号是否重复
						jrs.close();// 关闭数据库资源
						request.setAttribute("register", user_Register);// 将user储存到session中
						//skipPage(request, response, "/register.jsp");
						windows(request, response,"账号已存在，请重新命名！","home.jsp");//新建弹窗
						//newPage(request, response,"<script type='text/javascript'>alert('账号或密码错误');window.location.href='home.jsp';javascript:showDialog();</script>");
						return;
					}
				}
					// 密码确认
					if (Jpw2.equals(Jpw)) {
						/**
						 * 数据库录入
						 */
						jrs.moveToInsertRow();// 移动到插入行
						jrs.updateString(1, Jid);
						jrs.updateString(2, Jpw);
						jrs.updateFloat(3, 0);
						jrs.updateString(4, Jem);
						jrs.insertRow();// 向表插入一行记录
						jrs.close();// 关闭数据库资源
						//request.setAttribute("register", user_Register);// 将user储存到session中
						//skipPage(request, response, "/home.jsp");
						windows(request, response,"注册成功","home.jsp");//新建弹窗
						return;
					} else {
						jrs.close();// 关闭数据库资源
						request.setAttribute("register", user_Register);// 将user储存到session中
						windows(request, response,"两次密码输入不符！","home.jsp");//新建弹窗
						//skipPage(request, response, "/register.jsp");
						return;
					}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			windows(request, response,"产生错误，注册失败","home.jsp");//新建弹窗
		}
	}
}
