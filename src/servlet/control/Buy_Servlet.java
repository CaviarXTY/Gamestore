package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import utils.*;
import bean.data.*;

public class Buy_Servlet extends universal_Servlet {
	/**
	 * doPost 处理购买事件
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 数据初始处理
		String Gid = request.getParameter("id").trim();// 获取游戏id
		HttpSession session = request.getSession(true);// 创建session对象
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// 获取页面搜索字
		Game_Bean game = (Game_Bean) session.getAttribute("Game");// 获取页面搜索字
		
		if (login.getLogin_case()==false) {
			windows(request, response,"请重新登录","game.jsp");
		} else {
			try {
				// 数据库连接，表格获取
				Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
				s.lineDatabase("JSP");// 连接数据库
				ResultSet jrs = s.getTable("Users");// 获取表格
				s.setWrite();// 设置表格集可写入
				while (jrs.next()) {// 遍历验证
					if (login.getLogin_name().equals(jrs.getString(1))) {
						String own = jrs.getString(6);
						own = own + Gid + ";";
						jrs.updateString(6, own);
						jrs.updateRow();
						game.setOwn(true);
						session.setAttribute("Game", game);// 将game储存到session中
						windows(request, response,"购买成功","game.jsp");//页面转
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				windows(request, response,"请重新登录","game.jsp");
			}
		}

	}
}
