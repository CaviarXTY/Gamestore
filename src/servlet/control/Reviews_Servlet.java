package servlet.control;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.rowset.CachedRowSetImpl;

import utils.*;
import bean.data.*;

public class Reviews_Servlet extends universal_Servlet {

	/**
	 * doPost 发表评论
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 数据初始处理
		// String nid = request.getParameter("nid").trim();// 获取用户id
		//String gid = request.getParameter("gid").trim();// 获取游戏id
		String text = new String(request.getParameter("text").getBytes("ISO8859-1"),"gb2312").trim();// 获取内容

		HttpSession session = request.getSession(true);// 创建session对象
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// 获取页面
		Game_Bean game = (Game_Bean) session.getAttribute("Game");// 获取登录状态
		//DataByPage_Bean reviews = (DataByPage_Bean) session.getAttribute("ReviewsRoom");// 获取登录状态
		
		// 登录检查
		if (login.getLogin_case() == false) {
			windows(request, response, "请登录", "game.jsp");// 页面转发
			return;
		}
		// 检查用户是否拥有该游戏
		if(game.isOwn()==false){
			windows(request, response, "并未购买此游戏!", "game.jsp");// 新建弹窗
			return;
		}
		//内容检查
		if (text.equals("")) {
			// skipPage(request, response, "/register.jsp");
			windows(request, response, "输入不能为空!", "game.jsp");// 新建弹窗
			return;
		}
		try {
			// 数据库连接，表格获取
			Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
			s.lineDatabase("JSP");// 连接数据库
			ResultSet jrs;

			// 获取评论表
			jrs = s.getTable("Reviews");// 获取表格
			jrs.last();
			int totalRecord = jrs.getRow();// 获取列数
			s.setWrite();// 设置表格集可写入
			/**
			 * 数据库录入
			 */
			jrs.moveToInsertRow();// 移动到插入行
			jrs.updateString(1, totalRecord + "");
			jrs.updateString(2, login.getLogin_name());
			jrs.updateString(3, game.getGameID());
			jrs.updateString(4, text);
			jrs.insertRow();// 向表插入一行记录
			
			jrs = s.getData("Reviews", "gameid", "'%"
					+ game.getGameID() + "%'", true);// 数据库模糊搜索-类型
			CachedRowSetImpl rowSet = null;// 储存表中全部记录的行集对象
			DataByPage_Bean ReviewsRoom = null;// 创建Bean对象
			try {
				ReviewsRoom = (DataByPage_Bean) session
						.getAttribute("ReviewsRoom");// 获取数据对象
				if (ReviewsRoom == null) {
					ReviewsRoom = new DataByPage_Bean();// 创建JavaBean对象
					session.setAttribute("ReviewsRoom", ReviewsRoom);// 设置ssionse
				}
			} catch (Exception exp) {
				ReviewsRoom = new DataByPage_Bean();// 创建JavaBean对象
				session.setAttribute("ReviewsRoom", ReviewsRoom);
			}
			rowSet = new CachedRowSetImpl();
			rowSet.populate(jrs);
			ReviewsRoom.setRowSet(rowSet);
			session.setAttribute("ReviewsRoom", ReviewsRoom);//评论集重新刷新
			
			s.close();// 关闭数据库资源
			// request.setAttribute("register", user_Register);//
			// 将user储存到session中
			// skipPage(request, response, "/home.jsp");
			windows(request, response, "发表成功", "game.jsp");// 新建弹窗

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response, "产生错误，发表失败", "home.jsp");// 新建弹窗
		}

	}
}
