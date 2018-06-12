package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import utils.*;
import bean.data.*;

public class ChangePw_Servlet extends universal_Servlet {
	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 数据初始处理
		HttpSession session = request.getSession(true);// 创建session对象
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// 获取页面搜索字
		
		String Old = request.getParameter("oldpw").trim();// 获取旧密码
		String New = request.getParameter("newpw").trim();// 获取新密码
		String New2 = request.getParameter("newpw2").trim();// 确认新密码

		if (login == null) {
			windows(request, response, "请重新登录", "home.jsp");// 新建弹窗
		} else {
			if(New.equals(New2)){
				try {
					// 数据库连接，表格获取
					Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
					s.lineDatabase("JSP");// 连接数据库
					ResultSet jrs = s.getTable("Users");// 获取表格
					s.setWrite();// 设置表格集可写入
					while (jrs.next()) {// 遍历验证
						if (login.getLogin_name().equals(jrs.getString(1))) {
							if(Old.equals(jrs.getString(2))){
								jrs.updateString(2, New);
								jrs.updateRow();
								windows(request, response, "修改成功！", "user.jsp?javascript:document:user_form.submit();");// 新建弹窗
							}else{
								windows(request, response, "旧密码错误！", "user.jsp?javascript:document:user_form.submit();");// 新建弹窗
							}

						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					windows(request, response,"请重新登录","home.jsp");
				}
			}else{
				windows(request, response, "两次新密码不同！", "user.jsp?javascript:document:user_form.submit();");// 新建弹窗
				return;
			}

		}

	}
}
