package servlet.control;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.rowset.*;

import utils.*;
import bean.data.*;

public class User_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 数据初始处理
		HttpSession session = request.getSession(true);// 创建session对象
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// 获取页面搜索字
		// windows(request, response,login.getLogin_name(),"home.jsp");

		// //数据检查
		// if(login.getLogin_name()==""||login.getLogin_case()==false||login==null)
		// windows(request, response,"请重新登录！","home.jsp");//未登录

		try {
			// 数据库连接，表格获取
			Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
			s.lineDatabase("JSP");// 连接数据库
			ResultSet jrs = s.getTable("Users");// 获取用户列
			while (jrs.next()) {
				if (login.getLogin_name().equals(jrs.getString(1))) {
					User_Bean user = new User_Bean();
					user.setUserID(jrs.getString(1));// 用户id1
					user.setUserMoney(jrs.getFloat(3));// 用户余额3
					user.setUserEmil(jrs.getString(4));// 用户邮箱4
					user.setUserPicture(jrs.getString(5));// 游戏头像5
					user.setOwn(jrs.getString(6));// 购买记录6
					user.setOwnNum(0);
					String own= user.getOwn();
					if(own==null){
						user.setOwn("没有购买过游戏！");
					}else{
				        int bStr = own.length() / 5;
				        user.setOwnNum(bStr);
				        String str="";
				        LinkedList<String> ownlist = new LinkedList();
				        for (int i = 0; i < bStr; i++) {
				        	str=(String) own.subSequence(5 * i, 4 + 5 * i);
							System.out.print(str);
							ownlist.add(str);
						}
				        user.setUserBought(ownlist);
					}
					session.setAttribute("User", user);// 将user储存到session中
					skipPage(request, response, "user.jsp");
				}
			}
			// Iterator<String> car =
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"请重新登录","home.jsp");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"请重新登录","home.jsp");
		}

	}

}
