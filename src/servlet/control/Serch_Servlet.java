package servlet.control;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.rowset.*;

import utils.*;
import bean.data.*;

public class Serch_Servlet extends universal_Servlet {

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**type(0-全部，1-分类，2-搜索)
		 * "全部","动作","冒险","模拟","角色扮演","休闲","其它","热门","免费" 
		 * "00", "01", "02","03",  "04", "05", "06", "07","08"
		 */
		// 数据初始处理
		String keywords = request.getParameter("keywords").trim();// 获取页面搜索字
		String type = request.getParameter("type").trim();// 获取页面操作类型(0-全部，1-分类，2-搜索)
		
		// 数据库连接，表格获取
		Sql_Utils s = new Sql_Utils();// 建立连接数据库对象
		s.lineDatabase("JSP");// 连接数据库
		ResultSet jrs = null;// 创建指令行

		/**
		 * 数据检查
		 */
		if (type.equals("0"))
			jrs = s.getTable("Games");// 数据库搜索-所有
		else if (type.equals("1"))
			jrs = s.getData("Games", "type", "'%" + keywords + "%'", true);// 数据库模糊搜索-类型
		else if (type.equals("2")) {
			if (keywords.equals(""))
				windows(request, response, "搜索不能为空", "");// 错误提示
			else
				jrs = s.getData("Games", "name", "'%" + keywords + "%'",
						true);// 数据库模糊搜索-名字
		} else {
			// type="0";
			jrs = s.getTable("Game");// 数据库搜索-所有
		}
		
		/**
		 * 数据提取
		 */
		try {
			// ResultSetMetaData metaData =jrs.getMetaData();
			// int columnNum = metaData.getColumnCount();
			HttpSession session = request.getSession(true);//创建session对象
			CachedRowSetImpl rowSet = null;// 储存表中全部记录的行集对象
			DataByPage_Bean GameRoom = null;//创建Bean对象
			try {
				GameRoom = (DataByPage_Bean) session.getAttribute("GameRoom");//获取数据对象
				if (GameRoom == null) {
					GameRoom = new DataByPage_Bean();// 创建JavaBean对象
					session.setAttribute("GameRoom", GameRoom);//设置ssionse
				}
			} catch (Exception exp) {
				GameRoom = new DataByPage_Bean();// 创建JavaBean对象
				session.setAttribute("GameRoom", GameRoom);
			}
			rowSet = new CachedRowSetImpl();
			rowSet.populate(jrs);
			GameRoom.setRowSet(rowSet);
			session.setAttribute("GameRoom", GameRoom);
			s.close();// 关闭资源
			if (jrs == null)
				response.sendRedirect("home.jsp");//容错操作
			else
				response.sendRedirect("list.jsp");//页面跳转

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			windows(request, response, "加载失败", "home.jsp");// 新建弹窗
		}
	}

}
