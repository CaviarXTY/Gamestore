package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.sun.rowset.CachedRowSetImpl;

import utils.*;
import bean.data.*;

public class Game_Servlet extends universal_Servlet {
	public Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
	public ResultSet jrs;// 数据库back

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// tip(request, response,"type="+request.getParameter("type"));//测试

		// 数据初始处理
		HttpSession session = request.getSession(true);// 创建session对象
		String number = request.getParameter("gameid").trim();// 获取数据
		Login_Bean login = (Login_Bean) session.getAttribute("Login");// 获取登录状态

		Game_Bean game = new Game_Bean();// 创建数据模型
		// windows(request,response,number,"/home.jsp");
		try {
			// 数据库连接，表格获取
			Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
			s.lineDatabase("JSP");// 连接数据库

			// 检测是否已拥有
			if (login.getLogin_case() == true) {
				jrs = s.getData("Users", "own", "'%" + number + "%'", true);// 数据库模糊搜索-游戏
				while (jrs.next()) {
					if (login.getLogin_name().equals(jrs.getString(1)))
						game.setOwn(true);
					else
						game.setOwn(false);
				}
			}

			// 游戏数据处理
			jrs = s.getTable("Games");// 精准数据查询
			while (jrs.next()) {
				if (number.equals(jrs.getString(1))) {
					// windows(request,response,"name="+jrs.getString(2),"/home.jsp");
					game.setGameID(jrs.getString(1));// 游戏id
					game.setGameName(jrs.getString(2));// 游戏名称
					game.setGameCover(jrs.getString(3));// 图片路径
					game.setGamePrice(jrs.getFloat(4));// 游戏价格
					game.setGamePublisher(jrs.getString(6));// 游戏发行商
					game.setGameSystemRequirements(jrs.getString(7));// 游戏环境
					game.setGameText(jrs.getString(8));// 游戏文字描述
					game.setReleaseDate(jrs.getString(9));// 发行时间
					session.setAttribute("Game", game);// 将game储存到session中
					// skipPage(request, response,"/game.jsp");//页面转发
					// s.close();

					// 评论数据处理
					jrs = s.getData("Reviews", "gameid", "'%"
							+ game.getGameID() + "%'", true);// 数据库模糊搜索-类型
					/**
					 * 数据提取
					 */
					//	response.sendRedirect("game.jsp");// 容错操作
					// ResultSetMetaData metaData =jrs.getMetaData();
					// int columnNum = metaData.getColumnCount();
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
					session.setAttribute("Game", game);// 将game储存到session中
					session.setAttribute("ReviewsRoom", ReviewsRoom);
					s.close();// 关闭资源
					if (jrs == null)
						response.sendRedirect("game.jsp");// 容错操作
					else
						skipPage(request, response, "game.jsp");// 页面转发
					return;
				}
			}
			// jrs = s.getData("Games", "id", "'%" + number + "%'", true);//
			// 数据库模糊搜索-类型
			// game.setGameID(jrs.getString(1));// 游戏id
			// game.setGameName(jrs.getString(2));// 游戏名称
			// game.setGameCover(jrs.getString(3));// 图片路径
			// game.setGamePrice(jrs.getFloat(4));// 游戏价格
			// game.setGamePublisher(jrs.getString(6));// 游戏发行商
			// game.setGameSystemRequirements(jrs.getString(7));// 游戏环境
			// game.setGameText(jrs.getString(8));// 游戏文字描述
			// game.setReleaseDate(jrs.getString(9));// 发行时间
			// request.setAttribute("Game", game);// 将game储存到session中

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			windows(request, response, number, "home.jsp");// 新建弹窗
			// windows(request, response, "加载失败", "home.jsp");// 新建弹窗
		}catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			windows(request, response,"加载失败","home.jsp");
		}
	}
}
